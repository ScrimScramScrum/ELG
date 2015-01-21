(function(angular, SockJS, Stomp, _, undefined) {
  angular.module("chatApp.services", []).service("ChatService", ['$rootScope', '$q', '$timeout', '$http', function($rootScope, $q, $timeout, $http) {

    // var user = "test";
    
    var service = {}, listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, messageIds = [];
    
    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/ELG-prosjekt/chat";
    // service.SOCKET_URL = "/spring-ng-chat-master/chat";
    service.CHAT_TOPIC = "/topic/message.";
    service.CHAT_BROKER = "/app/chat.hei";

    // session stuff

    service.user_data = {};
    service.user_data.messages = {};
    service.user_data.message = "";
    service.user_data.receiver = "Ingen";
    service.user_data.sender = "";
    service.user_data.subs = {};
    service.user_data.messages2 = [];
    service.user_data.unread = false;
    service.user_data.hidden = true;
    service.user_data.not_logged_in = true;

    service.user_data.ok = false;

    service.getUser = function() {
      $http.get('/ELG-prosjekt/getuser').
        success(function(data, status, headers, config) {
          var u = data.trim();
          service.user_data.sender = u;
          // set chat topic 
          service.CHAT_TOPIC = "/topic/message." + u;
        }).
        error(function(data, status, headers, config) {
          // error message goes here
          console.log("error getting username");
        });
    }

    service.saveState = function() {
      sessionStorage.ChatService = angular.toJson(service.user_data);
    }

    function restoreState() {
      service.user_data = angular.fromJson(sessionStorage.ChatService);
      service.initialize();
      service.user_data.ok = true;
    }

    $rootScope.$on("savestate", service.saveState);

    // session stuff end
    
    service.receive = function() {
      return listener.promise;
    };
    
    service.send = function(sender, receiver, message) {
      service.CHAT_BROKER = "/app/chat" + "." + receiver;
      var date = new Date();
      var time1 = date.getTime();
      var id = Math.floor(Math.random() * 1000000);
      socket.stomp.send(service.CHAT_BROKER, {
        priority: 9
      }, JSON.stringify({
        message: message,
        id: id,
        time: time1,
        from: sender
      }));
      messageIds.push(id);
    };

    service.sendPing = function(sender) {
      socket.stomp.send("/app/chat.OnlineUsers", {
        priority: 9
      }, JSON.stringify({
        person: sender
      }));
    }
    
    var reconnect = function() {
      $timeout(function() {
        service.initialize();
      }, this.RECONNECT_TIMEOUT);
    };
    
    var getMessage = function(data) {
      var message = JSON.parse(data), out = {};
      out.message = message.message;
      out.time = new Date(message.time);
      out.sender = message.from;
      if (_.contains(messageIds, message.id)) {
        out.self = true;
        messageIds = _.remove(messageIds, message.id);
      }
      return out;
    };
    
    service.initialize = function() {
      socket.client = new SockJS(service.SOCKET_URL);
      socket.stomp = Stomp.over(socket.client);
      socket.stomp.connect({}, function() {
        socket.stomp.subscribe("/topic/OnlineUsers", function(data) {
          var temp = JSON.parse(data.body);
          var person = temp.person;
          if(service.user_data.subs[person] == null) {
            service.user_data.subs[person] = {};
            service.user_data.subs[person].name = person;
            service.user_data.subs[person].muted = false;
          }
          service.user_data.subs[person].ping = new Date();
          service.user_data.subs[person].isOnline = true;
        });
        socket.stomp.subscribe(service.CHAT_TOPIC, function(data) {
          listener.notify(getMessage(data.body));
        });
      });
      socket.stomp.onclose = reconnect;
    };

    // var initialize = function() {
      // must be here?...
    // }

    if (sessionStorage.ChatService) restoreState();

    service.getUser();
    // initialize();

    return service;
  }]);
})(angular, SockJS, Stomp, _);