(function(angular) {
  angular.module("chatApp.controllers", []).controller("ChatCtrl", ['$scope', 'ChatService', '$interval', function($scope, ChatService, $interval) {

    ChatService.setup(function() {
      $scope.user = ChatService.user_data;
    // });

    $scope.user.change_chat = function(newRes) {
      $scope.user.receiver = newRes;
      $scope.user.messages2 = $scope.user.messages[newRes];
      if($scope.user.messages[newRes] == null) {
        $scope.user.messages[newRes] = [];
      }
      $scope.user.subs[newRes].unread = false;
    };

    $scope.user.hide = function() {
      if(ChatService.CHAT_TOPIC != "/topic/message.") {
        if($scope.user.hidden) {
          document.getElementById("chat_window").style.display = "block";
          $scope.user.hidden = false;
          $scope.user.unread = false;
        } else {
          document.getElementById("chat_window").style.display = "none";
          $scope.user.hidden = true;
        }
      } else console.log("chat not loaded yet");
    }

    $scope.classforuserlist = function(value){
         if(value.unread)
                return "unread";
         else if(!value.isOnline)
             return "offline";
        else
             return "nothing";
    }
    
    $scope.user.addMessage = function() {
      var temp_message = $scope.user.message;
      $scope.user.message = "";

      if(temp_message.length == 0) return;

      ChatService.send($scope.user.sender, $scope.user.receiver, temp_message);

      var out = {};
      out.message = temp_message;
      out.time = new Date();
      out.sender = $scope.user.sender;
      out.self = true;

      if($scope.user.messages[$scope.user.receiver] == null) {
        $scope.user.messages[$scope.user.receiver] = [];
      }

      $scope.user.messages[$scope.user.receiver].push(out);
      // update message view
      $scope.user.messages2 = $scope.user.messages[$scope.user.receiver];
    };

    $scope.emailfilter = function(value) {
      var res = value.split("@");
      return res[0];
    }

    function update_message_view(receiver){
      if($scope.user.receiver == receiver) {
        $scope.user.messages2 = $scope.user.messages[receiver];
      }
    }

    $scope.user.changeUser = function() {
      ChatService.changeUser($scope.user.sender);
    };

    $interval(function() {
        ChatService.sendPing($scope.user.sender);
    }, 5000);

    $interval(function() {
        var newDate = new Date();
        for (var i in $scope.user.subs) {
          if(newDate - $scope.user.subs[i].ping > 15000) {
            $scope.user.subs[i].isOnline = false;
          }
        }
    }, 7500);

    ChatService.receive().then(null, null, function(message) {
      if($scope.user.messages[message.sender] == null) {
        $scope.user.messages[message.sender] = [];
      }
      if(!$scope.user.subs[message.sender].muted) {
        $scope.user.messages[message.sender].push(message);
        if($scope.user.receiver != message.sender) {
          $scope.user.subs[message.sender].unread = true;
        }
        if($scope.user.hidden) {
          $scope.user.unread = true;
        }
        update_message_view(message.sender);
      }
    });

  }); // end callback from setup

  }]);
})(angular);