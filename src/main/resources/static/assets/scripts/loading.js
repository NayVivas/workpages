function registerUser() {
    var loader = document.getElementById("loader");
    var registerButton = document.getElementById("registerButton");
    registerButton.disabled = true;
    loader.style.display = "block";
    setTimeout(function () {
      loader.style.display = "none";
      swal("¡Felicitaciones!", "Registro exitoso", "success");
      registerButton.disabled = false;
    }, 3000);
  }
  