const display = document.getElementById("p-tag");

$.ajax({
  type: 'GET',
  url: 'http://localhost:8080/api/v1/customers',
  success: function(result) {
    // displays the response as a string
    display.innerHTML = JSON.stringify(result);

    
  },
  error: function(result) {
    display.innerHTML = JSON.stringify(result);
  }
})
