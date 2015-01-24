(function(angular, SockJS, Stomp, _, undefined) {
  angular.module("chatApp.services", []).service("ChatService", ['$rootScope', '$q', '$timeout', '$http', function($rootScope, $q, $timeout, $http) {

    var service = {}, listener = $q.defer(), socket = {
      client: null,
      stomp: null
    }, messageIds = [];
    
    service.RECONNECT_TIMEOUT = 30000;
    service.SOCKET_URL = "/ws";
    service.CHAT_TOPIC = "/topic/message.";
    service.CHAT_BROKER = "/app/chat.hei";

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

    service.getUser = function(callback) {
      $http.get('getuser').
        success(function(data, status, headers, config) {
          var u = data.trim();
          service.user_data.sender = u;
          // set chat topic 
          service.CHAT_TOPIC = "/topic/message." + u;
          callback();
        }).
        error(function(data, status, headers, config) {
          // error message goes here
          console.log("error getting username");
        });
    }

    service.saveState = function() {
      sessionStorage.ChatService = angular.toJson(service.user_data);
    }

    function restoreState(callback) {
      var temp_sender = service.user_data.sender;
      service.user_data = angular.fromJson(sessionStorage.ChatService);
      // clear chat history if new user logged in and set correct sender
      if(service.user_data.sender != temp_sender && temp_sender.length > 0) {
        service.user_data = {};
        service.user_data.messages = {};
        service.user_data.message = "";
        service.user_data.receiver = "Ingen";
        service.user_data.sender = temp_sender;
        service.user_data.subs = {};
        service.user_data.messages2 = [];
        service.user_data.unread = false;
        service.user_data.hidden = true;
        service.user_data.not_logged_in = true;
      }
      if(!service.user_data.hidden) {
        document.getElementById("chat_window").style.display = "block";
      }
      callback();
    }


    $rootScope.$on("savestate", service.saveState);

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

    // get user first.. then run callbacks for restore state and initialize
    // do all the initializing with a callback to the controller
    // call it from controller to make sure its loaded in the view.

    service.setup = function(callback) {
      service.getUser(function() {
        if (sessionStorage.ChatService) {
          restoreState(function() {
            service.initialize();
            callback();
          });
        } else {
          service.initialize();
          callback();
        };
      });
    }

    return service;
  }]);
})(angular, SockJS, Stomp, _);