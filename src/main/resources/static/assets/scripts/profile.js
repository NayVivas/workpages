const { createApp } = Vue;

createApp({
    data() {
        return {
            currentPassword: '',
            newPassword: '',
            confirmNewPassword: '',
            currentEmail: '',
            newEmail: '',
            confirmNewEmail: '',
            password: '',
        };
    },

    created() {


    },
    mounted() { },
    methods: {

        changePassword() {
            if (!this.currentPassword || !this.newPassword || !this.confirmNewPassword) {
              Swal.fire({
                icon: "error",
                title: "Debes completar todos los campos",
                confirmButtonColor: "#198754",
              });
              return;
            }
            if (this.newPassword !== this.confirmNewPassword) {
              Swal.fire({
                icon: "error",
                title: "Las contraseñas no coinciden",
                text: this.error,
                confirmButtonColor: "#198754",
              });
              return;
            }
            Swal.fire({
              title: "¿Estás seguro?",
              text: "¿Deseas cambiar tu contraseña?",
              icon: "warning",
              showCancelButton: true,
              confirmButtonColor: "#198754",
              cancelButtonColor: "#dc3545",
              confirmButtonText: "Sí, cambiar",
              cancelButtonText: "Cancelar",
            }).then((result) => {
              if (result.isConfirmed) {
                axios
                  .post("/change-password", {
                    currentPassword: this.currentPassword,
                    newPassword: this.newPassword,
                    confirmNewPassword: this.confirmNewPassword,
                  })
                  .then(() => {
                    Swal.fire({
                      icon: "success",
                      title: "Contraseña actualizada con éxito",
                      showConfirmButton: false,
                      timer: 1500,
                    }).then(() => {
                      setTimeout(() => {
                        this.logout();
                      }, 1000);
                    });
                  })
                  .catch((error) => {
                    this.error = error.response.data;
                    Swal.fire({
                      icon: "error",
                      title: "No se pudo actualizar la contraseña",
                      text: this.error,
                      confirmButtonColor: "#198754",
                    });
                  });
              }
            });
          },

          changeEmail() {
            if (!this.currentEmail || !this.newEmail || !this.confirmNewEmail) {
              Swal.fire({
                icon: "error",
                title: "Debes completar todos los campos",
                confirmButtonColor: "#198754",
              });
              return;
            }
            if (this.newEmail !== this.confirmNewEmail) {
              Swal.fire({
                icon: "error",
                title: "El correo electrónico no coincide",
                text: this.error,
                confirmButtonColor: "#198754",
              });
              return;
            }
            Swal.fire({
              title: "¿Estás seguro?",
              text: "¿Deseas cambiar tu correo electrónico?",
              icon: "warning",
              showCancelButton: true,
              confirmButtonColor: "#198754",
              cancelButtonColor: "#dc3545",
              confirmButtonText: "Sí, cambiar",
              cancelButtonText: "Cancelar",
            }).then((result) => {
              if (result.isConfirmed) {
                axios
                  .post("/change-email", {
                    currentEmail: this.currentEmail,
                    newEmail: this.newEmail,
                    confirmNewEmail: this.confirmNewEmail,
                  })
                  .then(() => {
                    Swal.fire({
                      icon: "success",
                      title: "Correo electrónico actualizado con éxito",
                      showConfirmButton: false,
                      timer: 1500,
                    }).then(() => {
                      setTimeout(() => {
                        this.logout();
                      },1000);
                    });
                  })
                  .catch((error) => {
                    this.error = error.response.data;
                    Swal.fire({
                      icon: "error",
                      title: "No se pudo actualizar el correo electrónico",
                      text: this.error,
                      confirmButtonColor: "#198754",
                    });
                  });
              }
            });
          },

          deleteAccount() {
            Swal.fire({
              title: '¿Estás seguro?',
              text: "Esta acción eliminará tu cuenta de forma permanente.",
              icon: 'warning',
              input: 'password',
              inputPlaceholder: 'Ingresa tu contraseña',
              showCancelButton: true,
              confirmButtonColor: 'rgb(25, 135, 84)',
              cancelButtonColor: '#d33',
              confirmButtonText: 'Sí, eliminar cuenta'
            }).then((result) => {
              if (result.isConfirmed) {
                const password = result.value;
                Swal.fire({
                  title: 'Eliminando la cuenta',
                  html: 'Por favor espere un momento...',
                  timerProgressBar: true,
                  didOpen: () => {
                    Swal.showLoading()
                  }
                });
                axios.patch('/users/delete', null, {
                  params: { password: password }
                })
                  .then(() => {
                    Swal.fire({
                        icon: "success",
                        title: "Su cuenta se ha eliminado con exito",
                        showConfirmButton: false,
                        timer: 1500,
                      }).then(() => {
                        setTimeout(() => {
                          this.logout();
                        },1000);
                      });
                    })
                  .catch((error) => {
                    this.error = error.response.data;
                    Swal.fire({
                      icon: "error",
                      title: "No se pudo eliminar la cuenta",
                      text: this.error,
                      confirmButtonColor: "#DB504A",
                    });
                  });
              }
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
                      icon: 'Error',
                      title: 'Error',
                      text: this.error,
                      confirmButtonColor: "#DB504A"
                    })
                  });
        },

    },

    computed: {},
}).mount("#app");