# Note linhtinh =))))
Ajax sample:
```
var request = {
  "attribute": value
};
$.ajax({
    url: "url",
    type: 'POST/GET/PUT/DELETE...',
    data: JSON.stringify(request),
    contentType: "application/json;charset=utf-8",
    dataType: 'json', // data type from serve
    success: function (data) {
      ....
    },
    error: function(data) {
      ....
    }
})
```
