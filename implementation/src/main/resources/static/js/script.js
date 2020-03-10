const display = document.getElementById("p-tag");

const displayList = document.getElementById("ul-tag");

$.ajax({
  type: 'GET',
  url: 'http://localhost:8080/api/v1/customers',
  success: function(result) {
    // displays the response as a string
    display.innerHTML = JSON.stringify(result);

    // display each entry in a list
    result.forEach((entry) => {
      const newLine = document.createElement("li");
      newLine.innerHTML = JSON.stringify(entry);

      displayList.appendChild(newLine);
    })
  },
  error: function(result) {
    display.innerHTML = JSON.stringify(result);
  }
})
