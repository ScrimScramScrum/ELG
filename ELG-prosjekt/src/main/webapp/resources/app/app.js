(function(angular) {
  var app = angular.module("chatApp", [
    'chatApp.controllers',
    'chatApp.services', 'luegg.directives'
  ]);

   app.run(function($rootScope) {

   	console.log("test app.run 123123123");

    window.onbeforeunload = function(event) {
    	console.log("test onbefore UNLOAD 111");
      	$rootScope.$broadcast('savestate');
    };

  });

   app.filter('orderObjectBy', function() {
    return function(items, field, field2, reverse) {
      var filtered = [];
      angular.forEach(items, function(item) {
        filtered.push(item);
      });
      filtered.sort(function (a, b) {
        if(a[field] > b[field]) {
          return 1;
        }
        else if(a[field] < b[field]) {
          return -1;
        }
        else return 0;
      });
      filtered.sort(function (a, b) {
        if(a[field2] > b[field2]) {
          return 1;
        }
        else if(a[field2] < b[field2]) {
          return -1;
        }
        else return 0;
      });
      if(reverse) filtered.reverse();
      return filtered;
    };
  });

})(angular);