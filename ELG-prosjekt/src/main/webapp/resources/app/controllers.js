(function(angular) {
  angular.module("chatApp.controllers", []).controller("ChatCtrl", ['$scope', 'ChatService', '$interval', function($scope, ChatService, $interval) {

    $scope.user = ChatService.user_data;

    $scope.user.change_chat = function(newRes) {
      $scope.user.receiver = newRes;
      $scope.user.messages2 = $scope.user.messages[newRes];
      if($scope.user.messages[newRes] == null) {
        $scope.user.messages[newRes] = [];
      }
      $scope.user.subs[newRes].unread = false;
    };

    $scope.user.hide = function() {
      // $("#hide").click(function(){
      //   $("#chat_window").hide();
      //   $("#show").show();
      // });
      // $("#show").click(function(){
      //   $("#show").hide();
      //   $("#chat_window").show();
      // });
      if($scope.user.hidden) {
        $scope.user.hidden = false;
        $scope.user.unread = false;
      } else $scope.user.hidden = true;

    }
    
    $scope.user.addMessage = function() {
      ChatService.send($scope.user.sender, $scope.user.receiver, $scope.user.message);

      var out = {};
      out.message = $scope.user.message;
      out.time = new Date();
      out.sender = $scope.user.sender;
      out.self = true;

      if($scope.user.messages[$scope.user.receiver] == null) {
        $scope.user.messages[$scope.user.receiver] = [];
      }

      $scope.user.messages[$scope.user.receiver].push(out);
      // update message view
      $scope.user.messages2 = $scope.user.messages[$scope.user.receiver];
      $scope.user.message = "";
    };

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
        console.log("unread: ", $scope.user.unread);
        if($scope.user.hidden) {
          $scope.user.unread = true;
        }
        console.log("hidden: ", $scope.user.hidden);
        console.log("unread: ", $scope.user.unread);
        update_message_view(message.sender);
        // $scope.$apply();
      }
    });
  }]);
})(angular);