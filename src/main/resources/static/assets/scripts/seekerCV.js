const { createApp } = Vue;

createApp({
  data() {
    return {
      image: [],
      userData: [],
      userDataId: [],
      personalDataId: [],
      educationsId: [],
      experienceId: [],
      otherDataId: [],
      skillsId: [],
      languagesId: [],
      userSession: false,
      isLoggedIn: localStorage.getItem("userSession") || false,
      userImageUrl: '',
      defaultImageUrl: '/static/images/default.jpg',
      dataExperience: ''
    };
  },

  created() {

    const userSession = localStorage.getItem("userSession");
    if (userSession) {
      this.isLoggedIn = true;
    }

    this.loadDataUsers();

    const storedFileName = localStorage.getItem('imageFileName');
    if (storedFileName) {
      this.fileName = storedFileName;
      this.getImage();
    }
    const userId = localStorage.getItem("userId");
  },

  mounted() {

    window.addEventListener("storage", this.handleStorageEvent);

  },

  beforeUnmount() {
    window.removeEventListener("storage", this.handleStorageEvent);
  },


  methods: {

    handleStorageEvent(event) {
      if (event.key === "userSession") {
        this.isLoggedIn = event.newValue === "true";
      }
    },

    loadDataUsers() {
      this.idURL = new URL(window.location).searchParams.get("id");
      axios.get(`/api/users/seekers/${this.idURL}`)
        .then((res) => {
          this.usersData = res.data;
          this.image = this.usersData.images;
          this.userImageUrl = this.image || this.defaultImageUrl;
          this.personalDataId = this.usersData.personalData;
          this.educationsId = this.usersData.educations;
          this.experienceId = this.usersData.jobExperiences;
          console.log(this.experienceId)
          this.otherDataId = this.usersData.otherDatas;
          this.skillsId = this.usersData.skills;
          this.languagesId = this.usersData.languages;
    
 
          this.dataExperience = this.experienceId[0];
    
   
        });
    },

    downloadFile() {
      this.idURL = new URL(window.location).searchParams.get("id");

      axios.get(`/api/user/${this.idURL}/download`, { responseType: 'blob' })
        .then((response) => {
          const contentDisposition = response.headers['content-disposition'];
          const fileName = contentDisposition.split('filename=')[1].replace(/"/g, '');
          const blob = new Blob([response.data], { type: response.data.type });
          const url = window.URL.createObjectURL(blob);
          const link = document.createElement("a");
          link.href = url;
          link.setAttribute("download", fileName);
          document.body.appendChild(link);
          link.click();
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
  computed: {

    portalLink() {
      return this.isLoggedIn ? "myhome.html" : "./login.html";
    },

    portalText() {
      return this.isLoggedIn ? "Mi portal Garzón" : "Portal Web";
    }
  },
}).mount("#app");

