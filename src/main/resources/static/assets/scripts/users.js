const { createApp } = Vue;

createApp({
    data() {
        return {
            userImageUrl: [],
            userData: [],
            jobApplication: [],
            indice: -1,
            users: [],
            personalData: [],
            genders: [],
            activitys: [],
            civilStatusData: [],
            yesOrNo: [],
            activitycompany: [],
            levelexperience: [],
            areajob: [],
            areaStudys: [],
            stateEducation: [],
            typedisabilitys: [],
            reason: [],
            salary: [],
            title: [],
            school: [],
            countryEducation: [],
            state: [],
            initEducation: [],
            endEducation: [],
            disability: [],
            jobThisCompany: [],
            stages: [],
            jobaspireses: [],
            availabilitys: [],
            typeJobs: [],
            relationships: [],
            typetransport: [],
            languageS: [],
            levelLanguageS: [],
            datapersonal: [],
            dataEducations: [],
            dataEducationsData: [],
            datapersonalusers: [],
            otherDatas: [],
            languageData: [],
            typeStudys: [],
            selectExperience: [],
            selectExperiences: [],
            educationData: [],
            experienceJob: [],
            dataExperiences: [],
            dataOthersData: [],
            datalanguages: [],
            dataSkill: [],
            dataPersonal: [],
            firstName: '',
            lastName: '',
            email: '',
            birthday: '',
            document: '',
            civilStatus: '',
            children: '',
            amountChildren: '',
            ageChildren: '',
            phoneOne: '',
            phoneTwo: '',
            address: '',
            stateResidence: '',
            cityResidence: '',
            gender: '',
            company: '',
            activityCompany: '',
            job: '',
            levelExperience: '',
            areaJob: '',
            countryExperience: '',
            description: '',
            technicalSkills: '',
            softSkills: '',
            branchOffice: '',
            jobThisCompany: '',
            disability: '',
            typeDisability: '',
            reasonForResignation: '',
            selectionStage: '',
            stage: '',
            jobAspires: '',
            availability: '',
            typeJob: '',
            typejobs: '',
            rotary: '',
            jobWeekend: '',
            meetPeople: '',
            relationship: '',
            transport: '',
            languages: '',
            writeLevel: '',
            oralLevel: '',
            personalDataId: '',
            typeStudy: '',
            areaStudy: '',
            initExperience: '',
            endExperience: '',
            experiencesJobs: '',
            selectEducation: [],
            selectPersonalData: [],
            selectPersonalDatas: [],
            selectLanguage: [],
            formEducationsEditar: [],
            editarEducations: [],
            detallesCuentas: [],
            educationIdd: [],
            restPersonalData: [],
            cont: 0,
            link: {},
            othersDatas: [],
            salaryData: [],
            jobVacancy: [],
            showMessage: false,
            fileName: '',
            idUser: '',
            defaultImageUrl: '/static/images/default.jpg',
            imageUrl: '',
            file: null,
            imageUploaded: false,
            fileLoaded: false,
            idURL: '',
            showForm: true,
            hasFile: false,
            hasDatabaseFile: false,
            isLoggedIn: localStorage.getItem("userSession") === "true",
            pdfFiles: '',
            categoty: [],
            genderCategory: [],
            maritalStatusCategory: [],
            studyTypeCategory: [],
            educationStatusCategory: [],
            activityCategory: [],
            experienceLevelCategory: [],
            desiredPositionCategory: [],
            salaryRangeCategory: [],
            transportTypeCategory: [],
            branchCategory: [],
            retirementReasonCategory: [],
            disabilityTypeCategory: [],
            workAvailabilityCategory: [],
            jobTypeCategory: [],
            personalRelationshipCategory: [],
            applicationStateCategory: [],
            languageCategory: [],
            languageLevelCategory: [],
            vacancies: [],
            selectedBranchOffice: '',
            page: 1,
            branchCategory: [],
            vacancy: [],
            selectedData: null,
            activeApplication: []
        };
    },

    created() {
        const userSession = localStorage.getItem("userSession");
        if (userSession) {
            this.isLoggedIn = true;
        }
        this.enumsPersonalData();
        this.loadDataUsers();
        this.loadApplyJob();
        this.getApplicants();
        this.getApplicantsId();
        this.getItemsCategory();
        this.loadDataVacancies();
        this.loadLanguages();

        try {
            this.loadDataUsers()
        } catch (error) {
            console.error(error)
        }

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

    watch: {
        gender(newValue) {
            console.log('Valor de gender:', newValue);
        },
        civilStatus(newValue) {
            console.log('Valor de civilStatus:', newValue);
        }
    },


    methods: {

        redirectToPage() {
            window.location.href = this.portalLink();
          },
        
          portalLink() {
            return this.isLoggedIn ? "myhome.html" : "./login.html";
          },
        
          portalText() {
            return this.isLoggedIn ? "Mi portal Garzón" : "Portal Web";
          },
        /* Items */
        getItemsCategory() {
            axios.get("/api/items/categories")
                .then(response => {
                    this.itemsCategory = response.data;
                    this.nameCategory = this.itemsCategory.map(category => category.name);
                    this.genderCategory = this.itemsCategory.filter(category => category.name === "Genero");
                    this.genderId = this.genderCategory[0].id;
                    this.genderCategory = Object.keys(this.genderCategory[0].itemsCatalogueList).map(key => this.genderCategory[0].itemsCatalogueList[key]);
                    this.genderCategory = this.genderCategory.filter(category => category.activeItems === true);

                    this.maritalStatusCategory = this.itemsCategory.filter(category => category.name === "Estado civil");
                    this.maritalId = this.maritalStatusCategory[0].id;
                    this.maritalStatusCategory = Object.keys(this.maritalStatusCategory[0].itemsCatalogueList).map(key => this.maritalStatusCategory[0].itemsCatalogueList[key])
                    this.maritalStatusCategory = this.maritalStatusCategory.filter(category => category.activeItems === true);

                    this.studyTypeCategory = this.itemsCategory.filter(category => category.name === "Tipo de estudio");
                    this.typeStudyId = this.studyTypeCategory[0].id;
                    const mappedStudyTypeCategory = Object.keys(this.studyTypeCategory[0].itemsCatalogueList).map(key => this.studyTypeCategory[0].itemsCatalogueList[key])
                    this.studyTypeOptions = mappedStudyTypeCategory;
                    this.studyTypeCategory = this.studyTypeCategory.filter(category => category.activeItems === true);

                    this.activityCategory = this.itemsCategory.filter(category => category.name === "Actividad de la Compañia");
                    this.activityCompanyId = this.activityCategory[0].id;
                    const mappedActivityCategory = Object.keys(this.activityCategory[0].itemsCatalogueList).map(key => this.activityCategory[0].itemsCatalogueList[key])
                    this.activityCategory = mappedActivityCategory;
                    this.activityCategory = this.activityCategory.filter(category => category.activeItems === true);

                    this.educationStatusCategory = this.itemsCategory.filter(category => category.name === "Estado/educación");
                    this.stateStudyId = this.educationStatusCategory[0].id;
                    this.educationStatusCategory = Object.keys(this.educationStatusCategory[0].itemsCatalogueList).map(key => this.educationStatusCategory[0].itemsCatalogueList[key])
                    this.educationStatusCategory = this.educationStatusCategory.filter(category => category.activeItems === true);

                    this.experienceLevelCategory = this.itemsCategory.filter(category => category.name === "Nivel de experiencia");
                    this.experienceLevelCategoryId = this.experienceLevelCategory[0].id;
                    this.experienceLevelCategory = Object.keys(this.experienceLevelCategory[0].itemsCatalogueList).map(key => this.experienceLevelCategory[0].itemsCatalogueList[key])
                    this.experienceLevelCategory = this.experienceLevelCategory.filter(category => category.activeItems === true);

                    this.workAvailabilityCategory = this.itemsCategory.filter(category => category.name === "Disponibilidad horaria");
                    this.workAvailabilityCategoryId = this.workAvailabilityCategory[0].id;
                    this.workAvailabilityCategory = Object.keys(this.workAvailabilityCategory[0].itemsCatalogueList).map(key => this.workAvailabilityCategory[0].itemsCatalogueList[key])
                    this.workAvailabilityCategory = this.workAvailabilityCategory.filter(category => category.activeItems === true);

                    this.applicationStatusCategory = this.itemsCategory.filter(category => category.name === "Estado/postulación");
                    this.applicationStatusCategoryId = this.applicationStatusCategory[0].id;
                    this.applicationStatusCategory = Object.keys(this.applicationStatusCategory[0].itemsCatalogueList).map(key => this.applicationStatusCategory[0].itemsCatalogueList[key])
                    this.applicationStatusCategory = this.applicationStatusCategory.filter(category => category.activeItems === true);

                    this.applicationStateCategory = this.itemsCategory.filter(category => category.name === "Estado/Aplicación");
                    this.applicationStateCategoryId = this.applicationStateCategory[0].id;
                    this.applicationStateCategory = Object.keys(this.applicationStateCategory[0].itemsCatalogueList).map(key => this.applicationStateCategory[0].itemsCatalogueList[key])
                    this.applicationStateCategory = this.applicationStateCategory.filter(category => category.activeItems === true);

                    this.languageCategory = this.itemsCategory.filter(category => category.name === "Lenguajes");
                    this.languageCategoryId = this.languageCategory[0].id;
                    this.languageCategory = Object.keys(this.languageCategory[0].itemsCatalogueList).map(key => this.languageCategory[0].itemsCatalogueList[key])
                    this.languageCategory = this.languageCategory.filter(category => category.activeItems === true);

                    this.languageLevelCategory = this.itemsCategory.filter(category => category.name === "Nivel del idioma");
                    this.languageLevelCategoryId = this.languageLevelCategory[0].id;
                    this.languageLevelCategory = Object.keys(this.languageLevelCategory[0].itemsCatalogueList).map(key => this.languageLevelCategory[0].itemsCatalogueList[key])
                    this.languageLevelCategory = this.languageLevelCategory.filter(category => category.activeItems === true);

                    this.desiredPositionCategory = this.itemsCategory.filter(category => category.name === "Puesto al que aspira");
                    this.desiredPositionCategoryId = this.desiredPositionCategory[0].id;
                    this.desiredPositionCategory = Object.keys(this.desiredPositionCategory[0].itemsCatalogueList).map(key => this.desiredPositionCategory[0].itemsCatalogueList[key])
                    this.desiredPositionCategory = this.desiredPositionCategory.filter(category => category.activeItems === true);

                    this.retirementReasonCategory = this.itemsCategory.filter(category => category.name === "Razón del retiro");
                    this.retirementReasonCategoryId = this.retirementReasonCategory[0].id;
                    this.retirementReasonCategory = Object.keys(this.retirementReasonCategory[0].itemsCatalogueList).map(key => this.retirementReasonCategory[0].itemsCatalogueList[key])
                    this.retirementReasonCategory = this.retirementReasonCategory.filter(category => category.activeItems === true);

                    this.recruiterRoleCategory = this.itemsCategory.filter(category => category.name === "Rol");
                    this.recruiterRoleCategoryId = this.recruiterRoleCategory[0].id;
                    this.recruiterRoleCategory = Object.keys(this.recruiterRoleCategory[0].itemsCatalogueList).map(key => this.recruiterRoleCategory[0].itemsCatalogueList[key])
                    this.recruiterRoleCategory = this.recruiterRoleCategory.filter(category => category.activeItems === true);

                    this.personalRelationshipCategory = this.itemsCategory.filter(category => category.name === "Relaciones/personal");
                    this.personalRelationshipCategoryId = this.personalRelationshipCategory[0].id;
                    this.personalRelationshipCategory = Object.keys(this.personalRelationshipCategory[0].itemsCatalogueList).map(key => this.personalRelationshipCategory[0].itemsCatalogueList[key])
                    this.personalRelationshipCategory = this.personalRelationshipCategory.filter(category => category.activeItems === true);

                    this.salaryRangeCategory = this.itemsCategory.filter(category => category.name === "Rango de salario");
                    this.salaryRangeCategoryId = this.salaryRangeCategory[0].id;
                    this.salaryRangeCategory = Object.keys(this.salaryRangeCategory[0].itemsCatalogueList).map(key => this.salaryRangeCategory[0].itemsCatalogueList[key])
                    this.salaryRangeCategory = this.salaryRangeCategory.filter(category => category.activeItems === true);

                    this.transportTypeCategory = this.itemsCategory.filter(category => category.name === "Tipo de transporte");
                    this.transportTypeCategoryId = this.transportTypeCategory[0].id;
                    this.transportTypeCategory = Object.keys(this.transportTypeCategory[0].itemsCatalogueList).map(key => this.transportTypeCategory[0].itemsCatalogueList[key])
                    this.transportTypeCategory = this.transportTypeCategory.filter(category => category.activeItems === true);

                    this.branchCategory = this.itemsCategory.filter(category => category.name === "Sucursales");
                    this.branchCategoryId = this.branchCategory[0].id;
                    this.branchCategory = Object.keys(this.branchCategory[0].itemsCatalogueList).map(key => this.branchCategory[0].itemsCatalogueList[key])
                    this.branchCategory = this.branchCategory.filter(category => category.activeItems === true);

                    this.disabilityTypeCategory = this.itemsCategory.filter(category => category.name === "Tipo de discapacidad");
                    this.disabilityTypeCategoryId = this.disabilityTypeCategory[0].id;
                    this.disabilityTypeCategory = Object.keys(this.disabilityTypeCategory[0].itemsCatalogueList).map(key => this.disabilityTypeCategory[0].itemsCatalogueList[key])
                    this.disabilityTypeCategory = this.disabilityTypeCategory.filter(category => category.activeItems === true);

                    this.jobTypeCategory = this.itemsCategory.filter(category => category.name === "Tipo de trabajo");
                    this.jobTypeCategoryId = this.jobTypeCategory[0].id;
                    this.jobTypeCategory = Object.keys(this.jobTypeCategory[0].itemsCatalogueList).map(key => this.jobTypeCategory[0].itemsCatalogueList[key])
                    this.jobTypeCategory = this.jobTypeCategory.filter(category => category.activeItems === true);

                    this.studyTypeCategory = this.itemsCategory.filter(category => category.name === "Tipo de estudio");
                    this.studyTypeCategoryId = this.studyTypeCategory[0].id;
                    this.studyTypeCategory = Object.keys(this.studyTypeCategory[0].itemsCatalogueList).map(key => this.studyTypeCategory[0].itemsCatalogueList[key])
                    this.studyTypeCategory = this.studyTypeCategory.filter(category => category.activeItems === true);
                })
                .catch(error => {
                    this.error = error.response.data;
                })
        },

        /* Image Profile */
        select_file(event) {
            const file = event.target.files[0];
            this.file = file;
            const reader = new FileReader();
            reader.onload = (e) => {
                this.imageUrl = e.target.result;
                this.userImageUrl = e.target.result;
            };
            reader.readAsDataURL(file);
        },

        uploadImage() {
            this.fileName = null;
            if (this.fileName) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ya tienes una imagen de perfil guardada',
                    icon: 'error',
                    confirmButtonText: 'Ok',
                    confirmButtonColor: '#dc3545'
                });
            } else {
                const formData = new FormData();
                formData.append('file', this.file);
                Swal.fire({
                    title: 'Subiendo imagen',
                    text: 'Por favor espere...',
                    icon: 'info',
                    showConfirmButton: false,
                    timer: 2000
                });
                setTimeout(() => {
                    axios.post('/api/upload', formData)
                        .then(response => {
                            this.imageUploaded = true;
                            this.fileName = response.data.fileName;
                            axios.get(`/api/users/image`, { responseType: 'blob' })
                                .then(response => {
                                    this.imageUrl = window.URL.createObjectURL(response.data);
                                    this.userImageUrl = imageUrl;
                                    this.sortFiles();
                                })
                                .catch(error => {
                                    console.error(error);
                                });

                            Swal.fire({
                                title: 'Imagen subida',
                                text: 'La imagen se subió con éxito',
                                icon: 'success',
                                confirmButtonText: 'Ok',
                                confirmButtonColor: '#198754'
                            });
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
                }, 2000);
            }
        },

        /* Files PDF */
        onFileChange(event) {
            this.file = event.target.files[0]
        },

        uploadFile() {
            if (!this.file) {
                Swal.fire({
                    title: 'Error',
                    text: 'Por favor seleccione un archivo',
                    icon: 'error',
                    confirmButtonText: 'Ok',
                    confirmButtonColor: '#dc3545'
                });
                return;
            }

            if (this.fileName) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ya tienes un archivo guardado',
                    icon: 'error',
                    confirmButtonText: 'Ok',
                    confirmButtonColor: '#dc3545'
                });
            } else {
                const formData = new FormData();
                formData.append('file', this.file);
                Swal.fire({
                    title: 'Subiendo archivo',
                    text: 'Por favor espere...',
                    icon: 'info',
                    showConfirmButton: false,
                    timer: 2000
                });
                setTimeout(() => {
                    axios.post('/api/upload/files', formData)
                        .then(response => {
                            console.log(response.data);
                            this.fileName = response.data.url;
                            this.fileLoaded = true
                            Swal.fire({
                                title: 'Archivo subido',
                                text: 'El archivo se subió con éxito',
                                icon: 'success',
                                confirmButtonText: 'Ok',
                                confirmButtonColor: '#198754'
                            });
                            window.location.reload();
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
                }, 2000);
            }
        },

        showEditMessage() {
            this.showMessage = true;
        },

        /* Download file */
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

        /* Enums */
        enumsPersonalData() {

            axios.get("/api/enum/yesorno").then((res) => {
                this.yesOrNo = res.data;
            })

            axios.get("/api/enum/rol").then((res) => {
                this.rol = res.data;
            })

        },

        /* PersonalData */
        postPersonalData() {
            console.log(this.firstName)
            console.log(this.lastName)
            console.log(this.email)
            console.log(this.birthday)
            console.log(this.document)
            console.log(this.civilStatus)
            console.log(this.children)
            console.log(this.amountChildren)
            console.log(this.ageChildren)
            console.log(this.phoneOne)
            console.log(this.phoneTwo)
            console.log(this.address)
            console.log(this.stateResidence)
            console.log(this.cityResidence)
            console.log(this.gender)
            if (isNaN(this.amountChildren)) {
                console.log("error")
                return;
            }
            let personalData = {
                firstName: this.firstName,
                lastName: this.lastName,
                email: this.email,
                birthday: this.birthday,
                document: this.document,
                civilStatus: this.civilStatus,
                children: this.children,
                amountChildren: this.amountChildren,
                ageChildren: this.ageChildren,
                phoneOne: this.phoneOne,
                phoneTwo: this.phoneTwo,
                address: this.address,
                stateResidence: this.stateResidence,
                cityResidence: this.cityResidence,
                gender: this.gender
            };
            console.log(typeof this.amountChildren)

            axios
                .post("/api/users/current/personaldata", personalData)
                .then((response) => window.location.href = 'educations.html')
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


        loadPersonalData() {
            axios.get("/api/users/seekers/current").then((res) => {
                this.users = res.data;
                this.personalData = this.users.personalData;
                this.personalDataId = this.personalData[0].id;
            });
        },

        changePersonalData(link) {
            this.id = link.id;
            this.firstName = link.firstName;
            this.lastName = link.lastName;
            this.email = link.email;
            this.birthday = link.birthday;
            this.document = link.document;
            this.civilStatus = link.civilStatus;
            this.children = link.children;
            this.amountChildren = link.amountChildren;
            this.ageChildren = link.ageChildren;
            this.phoneOne = link.phoneOne;
            this.phoneTwo = link.phoneTwo;
            this.address = link.address;
            this.stateResidence = link.stateResidence;
            this.cityResidence = link.cityResidence;
            this.gender = link.gender;
            return link;
        },

        editPersonalData() {
            axios.patch(`/api/users/current/personaldata/${this.id}`,
                {
                    firstName: this.firstName,
                    lastName: this.lastName,
                    email: this.email,
                    birthday: this.birthday,
                    document: this.document,
                    civilStatus: this.civilStatus,
                    children: this.children,
                    amountChildren: this.amountChildren,
                    ageChildren: this.ageChildren,
                    phoneOne: this.phoneOne,
                    phoneTwo: this.phoneTwo,
                    address: this.address,
                    stateResidence: this.stateResidence,
                    cityResidence: this.cityResidence,
                    gender: this.gender
                }
            )
                .then((response) => window.location.reload())
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

        deletePersonalData() {
            axios.delete(`/api/users/current/personaldata/delete/${this.personalDataId}`)
                .then((res) => {
                    return window.location.reload();
                })
        },

        /* Job Experience */
        addJobExperienceNext() {
            let jobExperience = {
                company: this.company,
                activityCompany: this.activityCompany,
                job: this.job,
                levelExperience: this.levelExperience,
                areaJob: this.areaJob,
                countryExperience: this.countryExperience,
                description: this.description
            };
            axios
                .post(
                    "/api/users/current/jobexperience", jobExperience)
                .then((response) => window.location.href = 'othersData.html')
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

        addJobExperienceReload() {
            let jobExperience = {
                company: this.company,
                activityCompany: this.activityCompany,
                job: this.job,
                levelExperience: this.levelExperience,
                areaJob: this.areaJob,
                countryExperience: this.countryExperience,
                description: this.description
            };
            axios
                .post(
                    "/api/users/current/jobexperience", jobExperience)
                .then((response) => window.location.href())
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


       postJobExperience() {
            let jobExperience = {
                company: this.company,
                activityCompany: this.activityCompany,
                job: this.job,
                levelExperience: this.levelExperience,
                areaJob: this.areaJob,
                countryExperience: this.countryExperience,
                description: this.description
            };
            axios
                .post(
                    "/api/users/current/jobexperience", jobExperience)
                .then((response) => window.location.href = 'mycv.html')
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

        loadJobExperience() {
            axios.get("/api/users/seekers/current").then((res) => {
                this.users = res.data;
                console.log(this.users)

                this.experienceJob = this.users.jobExperiences
                console.log(this.experienceJob)


                this.selectExperiences = this.experienceJob.filter(
                    (dataP) => dataP.id == this.selectExperience
                );
                console.log(this.selectExperiences)

                this.experienceId = this.selectExperiences[0].id;
                console.log(this.experienceId)

            });
        },

        changeJobExperience(link) {
            this.id = link.id;
            this.company = link.company;
            this.activityCompany = link.activityCompany;
            this.job = link.job;
            this.levelExperience = link.levelExperience;
            this.areaJob = link.areaJob;
            this.countryExperience = link.countryExperience;
            this.initExperience = link.initExperience;
            this.endExperience = link.endExperience;
            this.description = link.description;
            return link;

        },

        editExperience() {
            axios.patch(`/api/users/current/jobexperience/${this.id}`,
                {
                    company: this.company,
                    activityCompany: this.activityCompany,
                    job: this.job,
                    levelExperience: this.levelExperience,
                    areaJob: this.areaJob,
                    countryExperience: this.countryExperience,
                    description: this.description
                }
            )
                .then((response) => window.location.reload())
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

        deleteExperience() {
            const modal = document.querySelector('#jobExperienceDataModal');
            const modalInstance = bootstrap.Modal.getInstance(modal);
            modalInstance.hide();
            Swal.fire({
                title: '¿Estás seguro de que quieres eliminar este elemento?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#198754',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Eliminar'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.delete(`/api/users/current/jobexperience/delete/${this.id}`)
                        .then((res) => {
                            window.location.href = 'mycv.html';
                            loader.style.display = 'none';
                            modalInstance.hide();
                        })
                        .catch(error => {
                            loader.style.display = 'none';
                        });
                } else {
                    modalInstance.show();
                    loader.style.display = 'none';
                }
            });
        },

        /* Educations */
        addEducationsNext() {
            let educations = {
                title: this.title,
                school: this.school,
                typeStudy: this.typeStudy,
                areaStudy: this.areaStudy,
                countryEducation: this.countryEducation,
                state: this.state,
                initEducation: this.initEducation,
                endEducation: this.endEducation
            };
            axios
                .post(
                    "/api/users/current/educations", educations)
                .then((response) => window.location.href = 'jobExperience.html')
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

        addEducationsReload() {
            let educations = {
                title: this.title,
                school: this.school,
                typeStudy: this.typeStudy,
                areaStudy: this.areaStudy,
                countryEducation: this.countryEducation,
                state: this.state,
                initEducation: this.initEducation,
                endEducation: this.endEducation
            };
            axios
                .post(
                    "/api/users/current/educations", educations)
                .then((response) => window.location.reload())
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


        postEducations() {
            let educations = {
                title: this.title,
                school: this.school,
                typeStudy: this.typeStudy,
                areaStudy: this.areaStudy,
                countryEducation: this.countryEducation,
                state: this.state,
                initEducation: this.initEducation,
                endEducation: this.endEducation
            };
            axios
                .post(
                    "/api/users/current/educations", educations)
                .then((response) => window.location.href = 'mycv.html')
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


        loadEducations() {
            axios.get("/api/users/seekers/current").then((res) => {
                this.users = res.data;
                console.log(this.users)

                this.educationData = this.users.educations
                console.log(this.educationData)

                this.selectEducations = this.educationData.filter(
                    (dataP) => dataP.id == this.selectEducation
                );
                console.log(this.selectEducations)

                this.educationId = this.selectEducations[0].id;
                console.log(this.educationId)

                return this.educationId;

            });
        },


        changeEducation(dataEducation) {
            this.id = dataEducation.id;
            this.title = dataEducation.title;
            this.school = dataEducation.school;
            this.typeStudy = dataEducation.typeStudy;
            this.areaStudy = dataEducation.areaStudy;
            this.countryEducation = dataEducation.countryEducation;
            this.state = dataEducation.state;
            this.initEducation = dataEducation.initEducation;
            this.endEducation = dataEducation.endEducation;
            return dataEducation;

        },

        editEducations() {
            axios.patch(`/api/users/current/educations/${this.id}`,
                {
                    title: this.title,
                    school: this.school,
                    typeStudy: this.typeStudy,
                    areaStudy: this.areaStudy,
                    countryEducation: this.countryEducation,
                    state: this.state,
                    initEducation: this.initEducation,
                    endEducation: this.endEducation
                }
            )
                .then((response) => window.location.reload())
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

        deleteEducations() {
            const modal = document.querySelector('#educationsDataModal');
            const modalInstance = bootstrap.Modal.getInstance(modal);
            modalInstance.hide();
            Swal.fire({
                title: '¿Estás seguro de que quieres eliminar este elemento?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#198754',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Eliminar'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.delete(`/api/users/current/educations/delete/${this.id}`)
                        .then((res) => {
                            window.location.href = 'mycv.html';
                            loader.style.display = 'none';
                            modalInstance.hide();
                        })
                        .catch(error => {
                            loader.style.display = 'none';
                        });
                } else {
                    modalInstance.show();
                    loader.style.display = 'none';
                }
            });
        },

        /* OtherData */
        addOtherData() {
            let otherData = {
                salary: this.salary,
                disability: this.disability,
                typeDisability: this.typeDisability,
                jobThisCompany: this.jobThisCompany,
                branchOffice: this.branchOffice,
                reasonForResignation: this.reasonForResignation,
                stage: this.stage,
                selectionStage: this.selectionStage,
                jobAspires: this.jobAspires,
                availability: this.availability,
                typeJob: this.typeJob,
                rotary: this.rotary,
                jobWeekend: this.jobWeekend,
                meetPeople: this.meetPeople,
                relationship: this.relationship,
                transport: this.transport
            };
            axios
                .post(
                    "/api/users/current/othersdata", otherData)
                .then((response) => window.location.href = "skills.html")
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

        loadOtherData() {
            axios.get("/api/users/seekers/current").then((res) => {
                this.users = res.data;
                this.othersDatas = this.users.otherDatas
                this.otherDataId = this.othersDatas[0].id;
            });
        },

        changeOtherData(link) {
            this.id = link.id;
            this.salary = link.salary;
            this.disability = link.disability;
            this.typeDisability = link.typeDisability;
            this.jobThisCompany = link.jobThisCompany;
            this.branchOffice = link.branchOffice;
            this.reasonForResignation = link.reasonForResignation;
            this.stage = link.stage;
            this.selectionStage = link.selectionStage;
            this.jobAspires = link.jobAspires;
            this.availability = link.availability;
            this.typeJob = link.typeJob;
            this.rotary = link.rotary;
            this.jobWeekend = link.jobWeekend;
            this.meetPeople = link.meetPeople;
            this.relationship = link.relationship;
            this.transport = link.transport;

            return link;

        },

        editOtherData() {
            axios.patch(`/api/users/current/othersdata/${this.id}`,
                {
                    salary: this.salary,
                    disability: this.disability,
                    typeDisability: this.typeDisability,
                    jobThisCompany: this.jobThisCompany,
                    branchOffice: this.branchOffice,
                    reasonForResignation: this.reasonForResignation,
                    stage: this.stage,
                    selectionStage: this.selectionStage,
                    jobAspires: this.jobAspires,
                    availability: this.availability,
                    typeJob: this.typeJob,
                    rotary: this.rotary,
                    jobWeekend: this.jobWeekend,
                    meetPeople: this.meetPeople,
                    relationship: this.relationship,
                    transport: this.transport
                }
            )
                .then((response) => window.location.reload())
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

        deleteOtherData() {
            axios.delete(`/api/users/current/othersdata/delete/${this.id}`)
                .then((res) => {
                    return window.location.reload();
                })
        },

        /* languagues */
        postLanguages() {
            let languages = {
                languages: this.languages,
                writeLevel: this.writeLevel,
                oralLevel: this.oralLevel
            };

            axios
                .post("/api/users/current/languages", languages)
                .then((response) => window.location.href = '/seeker/mycv.html')
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

        loadLanguages() {
            axios.get("/api/users/seekers/current").then((res) => {
                this.users = res.data;
                console.log(this.users)

                this.languageData = this.users.languages
                console.log(this.languageData)


                /* this.selectLanguages = this.languageData.filter(
                    (dataP) => dataP.id == this.selectLanguage
                );
                console.log(this.selectLanguages) */

                this.languagesId = this.selectLanguages[0].id;
                console.log(this.languagesId)

            });
        },

        changeLanguages(link) {
            this.id = link.id;
            this.languages = link.languages;
            this.writeLevel = link.writeLevel;
            this.oralLevel = link.oralLevel;

            return link;

        },

        editLanguages() {
            axios.patch(`/api/users/current/languages/${this.id}`,
                {
                    languages: this.languages,
                    writeLevel: this.writeLevel,
                    oralLevel: this.oralLevel
                }
            )
                .then((response) => window.location.reload())
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


        deleteLanguages() {
            const modal = document.querySelector('#languagesModal');
            const modalInstance = bootstrap.Modal.getInstance(modal);
            modalInstance.hide();
            Swal.fire({
                title: '¿Estás seguro de que quieres eliminar este elemento?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#198754',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Eliminar'
            }).then((result) => {
                if (result.isConfirmed) {
                    axios.delete(`/api/users/current/languages/delete/${this.id}`)
                        .then((res) => {
                            window.location.href = 'mycv.html';
                            loader.style.display = 'none';
                            modalInstance.hide();
                        })
                        .catch(error => {
                            loader.style.display = 'none';
                        });
                } else {
                    modalInstance.show();
                    loader.style.display = 'none';
                }
            });
        },

        /* Skills */
        addSkills() {
            if (
                this.technicalSkills &&
                this.softSkills
            ) {
                let skills = {
                    technicalSkills: this.technicalSkills,
                    softSkills: this.softSkills
                };
                this.postSkills(skills);
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Debes completar todos los campos",
                    confirmButtonColor: "#20DBC2",
                });
            }
        },

        postSkills() {
            axios
                .post(
                    "/api/users/current/skills",
                    "technicalSkills=" +
                    this.technicalSkills +
                    "&softSkills=" +
                    this.softSkills,
                )
                .then((response) => window.location.reload())
                .catch((error) => {
                    this.error = error.response.data;
                    console.log(this.error);
                    Swal.fire({
                        icon: "error",
                        title: "Upss..",
                        text: this.error,
                        confirmButtonColor: "#20DBC2",
                    });
                });
        },

        loadSkills() {
            axios.get("/api/users/seekers/current").then((res) => {
                this.users = res.data;
                console.log(this.users)

                this.skillsData = this.users.skills
                console.log(this.skillsData)

                this.skillsId = this.skillsData[0].id;
                console.log(this.skillsId)

            });
        },

        changeSkills(link) {
            this.id = link.id;
            this.technicalSkills = link.technicalSkills;
            this.softSkills = link.softSkills;

            return link;

        },

        editSkills() {
            axios.patch(`/api/users/current/skills/${this.id}`,
                {
                    technicalSkills: this.technicalSkills,
                    softSkills: this.softSkills
                }
            )
                .then((response) => window.location.reload())
                .catch((error) => {
                    this.error = error.response.data;
                    Swal.fire({
                        icon: "error",
                        title: "Upss..",
                        text: this.error,
                        confirmButtonColor: "#20DBC2",
                    });
                });
        },

        deleteSkills() {
            axios.delete(`/api/users/current/skills/delete/${this.skillsId}`)
                .then((res) => {
                    return window.location.reload();
                })
        },

        /* Vacancies */
        applyJob() {
            const userSession = localStorage.getItem("userSession");
            
            if (userSession === "true") {
              Swal.fire({
                title: 'Aplicando a la vacante',
                html: 'Por favor espere un momento...',
                timerProgressBar: true,
                didOpen: () => {
                  Swal.showLoading()
                }
              });
          
              axios.post(`/api/apply/${this.idURL}`)
                .then(response => {
                  Swal.fire({
                    icon: 'success',
                    title: '¡Postulación exitosa!',
                    showConfirmButton: false,
                    timer: 1500
                  }).then(() => {
                    window.location.reload();
                  })
                })
                .catch(error => {
                  this.error = error.response.data;
                  Swal.fire({
                    icon: 'error', // La "e" debe ser minúscula
                    title: 'Error',
                    text: this.error,
                    confirmButtonColor: "#DB504A"
                  });
                });
            } else {
              Swal.fire({
                icon: 'info', // Puedes usar el icono de información para el mensaje
                title: 'Iniciar sesión',
                text: 'Debes iniciar sesión para aplicar a la vacante.',
                confirmButtonColor: "#DB504A"
              }).then(() => {
                window.location.href = "../login.html";
              });
            }
          },

        loadDataVacancies() {
            this.idURL = new URL(window.location).searchParams.get("id");
            console.log(this.idURL);

            axios.get(`/api/jobvacancy/${this.idURL}`)
                .then((res) => {
                    this.vacancy = res.data;
                });

            axios.get(`/api/jobvacancy`)
                .then((res) => {
                    this.jobVacancy = res.data
                })
        },

        loadVacancies() {
            console.log(this.selectedBranchOffice.description)
            axios.get(`/api/jobvacancies?branchOffice=${this.selectedBranchOffice.description}`)
                .then((response) => {
                    this.vacancies = response.data;
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

        /* Applications */
        loadApplyJob() {
            axios.get(`/api/users/seeker/current/apply`)
                .then((res) => {
                    this.jobApplication = res.data;
                    console.log(this.jobApplication)

                    this.activeApplication = this.jobApplication.filter(jobApplications => jobApplications.vacancyActive === true);
                    console.log(this.activeApplication)
                })
        },

        getApplicants() {
            axios.get('/api/jobapplications')
                .then(response => {
                    this.applicants = response.data;
                })
                .catch(error => {
                    this.error = error.response.data;
                });
        },

        getApplicantsId() {
            axios.get(`/api/jobapplication/${this.idURL}`)
                .then((res) => {
                    this.seekerApplications = res.data;
                    this.applicationSeeker = this.seekerApplications.map(application => {
                        application.seeker;
                    })
                })
                .catch((error) => {
                    this.error = error.response.data;
                })
        },

        /* Users*/
        loadDataUsers() {
            axios.get(`/api/users/seekers/${this.idURL}`)
                .then((res) => {
                    this.usersData = res.data;
                    this.image = this.usersData.images;
                    this.userImageUrl = this.image || this.defaultImageUrl;
                    this.personalDataId = this.usersData.personalData;
                    this.educationsId = this.usersData.educations;
                    this.experienceId = this.usersData.jobExperiences;
                    this.otherDataId = this.usersData.otherDatas;
                    this.skillsId = this.usersData.skills;
                    this.languagesId = this.usersData.languages;
                    this.filesImage = this.usersData.files[0];
                });

            axios.get(`/api/users/seekers/current`)
                .then((res) => {
                    this.userData = res.data
                    this.image = this.userData.images;
                    this.userImageUrl = this.image || this.defaultImageUrl;
                    this.idUser = this.userData.id;
                    this.dataPersonal = this.userData.personalData[0];
                    this.dataEducations = this.userData.educations;
                    console.log(this.dataEducations)
                    this.dataExperiences = this.userData.jobExperiences;
                    this.dataOthersData = this.userData.otherDatas[0];
                    console.log(this.dataOthersData)
                    this.datalanguages = this.userData.languages;
                    this.dataSkill = this.userData.skills[0];
                    this.filePDF = this.userData.files;
                    if (this.filePDF) {
                        this.hasDatabaseFile = true;
                    }
                    this.pdfFiles = this.filePDF.split('/').pop();
                    /*   this.userDataId = this.userData.filter((data) => data.id == this.idURL) */
                })

            axios.get(`/api/users/seekers`)
                .then((res) => {
                    this.allData = res.data;
                    this.filePDF = this.allData[0].files;
                    this.dataEducationsData = this.allData[0].educations;
                    this.titleUsers = this.dataEducationsData.map(p => p.title);
                    this.filterSeekers = this.allData;
                })

            axios.get(`/api/othersdata`)
                .then((res) => {
                    this.allOthersData = res.data;
                })

            axios.get(`/api/personaldata`)
                .then((res) => {
                    this.allPersonalData = res.data;
                })
        },

        clearFilters() {
            this.filterSelectGender = null;
            this.filterSelectCivilStatus = null;
            this.filterSelectChildren = null;
            this.filterSelectSalary = null;
            this.filterSelectTitle = null;
            this.message = '';
        },

        onModalOpen(applicationId) {
            this.selectedApplicationId = applicationId;
        },

         /* Pagination */
         redirectToPage() {
            const userSession = localStorage.getItem("userSession");
            if (userSession === "true") {
                window.location.href = "myhome.html";
            } else {
                window.location.href = "../login.html";
            }
        },

        changePage(newPage) {
            this.page = newPage;
        },


        /* Logout */
        logout() {
            axios.post(`/api/logout`)
                .then(response => {
                    localStorage.removeItem('userSession');
                    localStorage.removeItem('imageFileName_' + this.idUser);
                    window.location.href = '/index.html';
                })
                .catch(error => {
                    this.error = error.response.data;
                });
        },
    },


    computed: {
        searchVacancies() {
            return this.jobVacancy.filter(item => {
                return item.jobVacancyTitle.toLowerCase().includes(this.messageVacancies.toLowerCase())
            })
        },

        pageCount() {
            return Math.ceil(this.vacancies.length / 6);
        },
        displayedVacancies() {
            const start = (this.page - 1) * 6;
            const end = start + 6;
            return this.vacancies.slice(start, end);
        },

        portalLink() {
            return this.isLoggedIn ? "myhome.html" : "./login.html";
        },

        portalText() {
            return this.isLoggedIn ? "Mi portal Garzón" : "Portal Web";
        }
    },
}).mount("#app");

