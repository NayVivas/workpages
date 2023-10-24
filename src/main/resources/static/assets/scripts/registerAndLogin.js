const { createApp } = Vue;

createApp({
  data() {
    return {
      users: [],
      sendEmail: [],
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      passwordError: null,
      personalData: [],
      allEmail: [],
      hasLowercase: false,
      hasUppercase: false,
      hasNumber: false,
      passwordVisible: false,
      userSession: false,
    };
  },

  created() {
    this.loadUsers();

  },
  mounted() { },
  methods: {

    loadUsers() {
      axios.get(`/api/users`)
        .then((res) => {
          this.allUser = res.data;
          this.allEmail = this.allUser.map(user => user.email);
        })
    },

    login(event) {
      event.preventDefault();
      axios
        .post("/api/login", `email=${this.email}&password=${this.password}`)
        .then((response) => {
          axios.get('/api/users/current')
            .then((userReponse) => {
              const userResp = userReponse.data;
              console.log(userResp);
              localStorage.setItem("userSession", true);
              if (this.email.includes("@admin") || (userResp.rol === "ADMIN")) {
               window.location.href = '/admin/myhome.html'
              } else if (userResp.rol === "RECRUITER") {
                window.location.href = '/recruiters/myhome.html'
              } else {
                if (userResp.formsComplete) {
                  window.location.href = '/seeker/myhome.html';
                } else {
                  if(!userResp.formsComplete) {
                    window.location.href = '/seeker/formsCV/personalData.html';
                  } else if (userResp.formsCompletePerdonalData) {
                    window.location.href = '/seeker/formsCV/educations.html';
                  } else if (userResp.formsCompleteEducations) {
                    window.location.href = '/seeker/formsCV/jobExperience.html';
                  } else if (userResp.formsCompleteJobExperince) {
                    window.location.href = '/seeker/formsCV/othersData.html';
                  } else if (userResp.formsCompleteOthersData) {
                    window.location.href = '/seeker/formsCV/othersData.html';
                  }
                }
              }
            })
            .catch((error) => {
              Swal.fire({
                confirmButtonColor: '#dc3545',
                icon: 'error',
                title: 'Error de inicio de sesión',
                text: 'Las credenciales ingresadas son incorrectas. Por favor, inténtelo de nuevo.'
              });
              this.error = error.response.data;
            });
        })
        .catch((error) => {
          Swal.fire({
            confirmButtonColor: '#dc3545',
            icon: 'error',
            title: 'Error de inicio de sesión',
            text: 'Las credenciales ingresadas son incorrectas. Por favor, inténtelo de nuevo.'
          });
          this.error = error.response.data;
        });
    },
    
    

    addUsers() {
      if (
        this.firstName &&
        this.lastName &&
        this.email.includes("@")
      ) {
        let user = {
          firstName: this.firstName,
          lastName: this.lastName,
          emailClient: this.email,
          password: this.password,
        };
        const loader = document.getElementById("loader");
        loader.style.display = "block"; // Mostrar el loader antes de la solicitud
        this.postUser(user);
      }
    },
    
    postUser(user) {
      axios
        .post(
          "/api/save/users",
          "firstName=" +
            user.firstName +
            "&lastName=" +
            user.lastName +
            "&email=" +
            user.emailClient +
            "&password=" +
            user.password
        )
        .then((response) => {
          const loader = document.getElementById("loader");
          loader.style.display = "none"; // Ocultar el loader después de la solicitud exitosa
    
          Swal.fire({
            title: "Felicidades te has registrado con éxito!!",
            showCancelButton: true,
            confirmButtonColor: "#198754",
            confirmButtonText: "OK",
          }).then((result) => {
            if (result.isConfirmed) {
              window.location.reload();
            }
          });
        })
        .catch((error) => {
          const loader = document.getElementById("loader");
          loader.style.display = "none"; // Ocultar el loader en caso de error
    
          this.error = error.response.data;
          Swal.fire({
            icon: "error",
            title: "Upss..",
            text: this.error,
            confirmButtonColor: "#dc3545",
          });
        });
    },    
    

    

    checkPassword() {
      this.hasLowercase = /[a-z]/.test(this.password);
      this.hasUppercase = /[A-Z]/.test(this.password);
      this.hasNumber = /\d/.test(this.password);
    },

    togglePasswordVisibility() {
      this.passwordVisible = !this.passwordVisible;
      const passwordInput = document.getElementById("password-input");
      passwordInput.type = this.passwordVisible ? "text" : "password";
    },

    resetPassword() {
      if (!this.allEmail.includes(this.email)) {
        Swal.fire({
          title: 'Error!',
          text: 'El correo ingresado no existe en la base de datos.',
          icon: 'error',
          confirmButtonText: 'OK'
        });
        return;
      }

      if (this.email.includes("@")) {
        let users = {
          emailClient: this.emailValue,
        };
        this.postReset(users);
      }
    },

    postReset() {
      axios
        .post(
          "/forgot-password",
          "email=" +
          this.email
        )
        .then((response) => {
          Swal.fire({
            title: "Haz recibido un correo electronico para reestablecer la contrasena, revisa tu bandeja de entrada",
            showCancelButton: true,
            confirmButtonColor: "#198754",
            confirmButtonText: "OK",
          }).then((result) => {
            if (result.isConfirmed) {
              window.location.href = "/login.html";
            }
          });
        }
        )
        .catch(error => {
          this.error = error.response.data;
          Swal.fire({
            icon: 'Error',
            title: 'Error',
            text: this.error,
            confirmButtonColor: "#DB504A"
          })
        });
    },

    logout() {
      axios.post(`/api/logout`)
          .then(response => {
              localStorage.removeItem('userSession');
              localStorage.removeItem('imageFileName_' + this.idUser);
              window.location.href = '/index.html';
          })
          .catch(error => {
              this.error = error.response.data;
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: this.error,
                confirmButtonColor: "#DB504A"
              })
            });
  },


  },

  computed: {

  },
}).mount("#app");