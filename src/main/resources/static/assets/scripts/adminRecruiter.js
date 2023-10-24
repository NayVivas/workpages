const { createApp } = Vue;

createApp({
    data() {
        return {
            recruiters: [],
            recruitersData: [],
            recruiterData: [],
            vacancyRecruiter: [],
            recruiterDataId: [],
            recruiter: [],
            jobVacancyTitle: '',
            jobVacancyDescription: '',
            jobVacancySkills: '',
            experienceRequired: '',
            levelExperience: '',
            levelexperience: [],
            postedBy: [],
            roleCategory: '',
            messageVacancies: '',
            messageRecruiter: '',
            messageVacanciesRecruiter: '',
            vacancyActive: true,
            experienceLevelCategory: [],
            branchCategory: [],
            recruiterData: '',
            postVacancyAdmin: [],
            recruiterCurrent: [],
            rols: [],
            addVacancy: [],
            vacancyRCurrent: [],
            experienceLevelCategory: '',
            roleCategory: '',
            vacancyAdmin: {

            },
            jobVacancyTitle: '',
            jobVacancyDescription: '',
            jobVacancySkills: '',
            branchOffices: [],
            branchOffice: [],
            experienceRequired: '',
            levelExperience: [],
            rols: [],
            postedBy: [],
            jobVacancy: [],
            jobVacancyId: [],
            message: "",
            filterSelectBranchOffice: '',
            filterSelectRecruiterRol: '',
            roleCategory: '',
            vacancyActive: true,
            userSession: false,
            isLoggedIn: localStorage.getItem("userSession") || false,
            branchCategory: [],
            experienceLevelCategory: [],
            selectedVacancyId: null,
            allData: [],
            genderCategory: '',
            filterSelectGender: '',
            filterSelectCivilStatus: '',
            yesOrNo: [],
            filterSelectChildren: '',
            filterSelectSalary: '',
            filterSelectTitle: '',
            salaryRangeCategory: '',
            maritalStatusCategory: '',
            titleUsers: '',
            applicationStateCategory: [],
            selectedState: [],
            selectedApplicationId: [],
            vacancies: [],
            seekerApplications: [],
            users: [],

        };
    },

    created() {
        const userSession = localStorage.getItem("userSession");
        if (userSession) {
            this.isLoggedIn = true;
        }
        this.getRecruiters();
        this.getItemsCategory();
        this.enumsPersonalData();
        this.loadDataVacancies();
        this.getJobVacancy();
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

        getItemsCategory() {
            axios.get("/api/items/categories")
                .then(response => {
                    this.itemsCategory = response.data;
                    this.nameCategory = this.itemsCategory.map(category => category.name);
                    this.genderCategory = this.itemsCategory.filter(category => category.name === "Genero");
                    this.genderId = this.genderCategory[0].id;
                    this.genderCategory = Object.keys(this.genderCategory[0].itemsCatalogueList).map(key => this.genderCategory[0].itemsCatalogueList[key]);
                    this.genderCategory = this.genderCategory.filter(category => category.activeItems === true);
                    this.genderCategory = this.genderCategory.sort((a, b) => {
                        if (a.description < b.description) {
                            return -1;
                        }
                        if (a.description > b.description) {
                            return 1;
                          }
                          return 0;
                    })

                    this.maritalStatusCategory = this.itemsCategory.filter(category => category.name === "Estado civil");
                    this.maritalId = this.maritalStatusCategory[0].id;
                    this.maritalStatusCategory = Object.keys(this.maritalStatusCategory[0].itemsCatalogueList).map(key => this.maritalStatusCategory[0].itemsCatalogueList[key])
                    this.maritalStatusCategory = this.maritalStatusCategory.filter(category => category.activeItems === true);
                    this.maritalStatusCategory = this.maritalStatusCategory.sort((a, b) => {
                        if (a.description < b.description) {
                            return -1;
                        }
                        if (a.description > b.description) {
                            return 1;
                          }
                          return 0;
                    })

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

                    /*  this.studyAreaCategory = this.itemsCategory.filter(category => category.name === "Área de estudio");
                     this.areaStudyId = this.studyAreaCategory[0].id;
                     this.studyAreaCategory = Object.keys(this.studyAreaCategory[0].itemsCatalogueList).map(key => this.studyAreaCategory[0].itemsCatalogueList[key]) */

                    this.educationStatusCategory = this.itemsCategory.filter(category => category.name === "Estado/educación");
                    this.stateStudyId = this.educationStatusCategory[0].id;
                    this.educationStatusCategory = Object.keys(this.educationStatusCategory[0].itemsCatalogueList).map(key => this.educationStatusCategory[0].itemsCatalogueList[key])
                    this.educationStatusCategory = this.educationStatusCategory.filter(category => category.activeItems === true);

                    this.experienceLevelCategory = this.itemsCategory.filter(category => category.name === "Nivel de experiencia");
                    this.experienceLevelCategoryId = this.experienceLevelCategory[0].id;
                    this.experienceLevelCategory = Object.keys(this.experienceLevelCategory[0].itemsCatalogueList).map(key => this.experienceLevelCategory[0].itemsCatalogueList[key])
                    this.experienceLevelCategory = this.experienceLevelCategory.filter(category => category.activeItems === true);

                    /*   this.jobAreaCategory = this.itemsCategory.filter(category => category.name === "Área de trabajo");
                      this.jobAreaCategoryId = this.jobAreaCategory[0].id;
                      this.jobAreaCategory = Object.keys(this.jobAreaCategory[0].itemsCatalogueList).map(key => this.jobAreaCategory[0].itemsCatalogueList[key])
                      console.log(this.jobAreaCategory); */

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
                    this.branchCategory = this.branchCategory.sort((a, b) => {
                        if (a.description < b.description) {
                            return -1;
                        }
                        if (a.description > b.description) {
                            return 1;
                          }
                          return 0;
                    })

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

        enumsPersonalData() {
            axios.get("/api/enum/yesorno").then((res) => {
                this.yesOrNo = res.data;
            })
        },

        getRecruiters() {
            this.idURL = new URL(window.location).searchParams.get("id");

            axios.get(`/api/users/recruiter/${this.idURL}`)
                .then((res) => {
                    this.recruiterData = res.data;
                    this.vacancyRecruiter = this.recruiterData.jobVacancies
                });

            axios.get(`/api/users/recruiters`)
                .then((res) => {
                    this.recruitersData = res.data;
                    this.recruitersData = this.recruitersData.filter(recruiter => recruiter.activeRecruiter === true);
                    console.log(this.recruitersData);
                    this.filterRecruiter = this.recruiterData;
                    
                })

            axios.get(`/api/users/recruiter/current`)
                .then((res) => {
                    this.recruiterCurrent = res.data;
                    console.log(this.recruiterCurrent);

                    this.vacancyRCurrent = this.recruiterCurrent.jobVacancies
                    console.log(this.vacancyRCurrent)

                    return this.vacancyRCurrent.sort((a, b) => {
                        if (a.vacancyActive === b.vacancyActive) {
                            return 0;
                        } else if (a.vacancyActive && !b.vacancyActive) {
                            return -1;
                        } else {
                            return 1;
                        }
                    });
                })
        },

        /* Vacancy */

        postVacancy() {
            axios.post("/api/recruiter/jobVacancy", {
                jobVacancyTitle: this.jobVacancyTitle,
                jobVacancyDescription: this.jobVacancyDescription,
                jobVacancySkills: this.jobVacancySkills,
                experienceRequired: this.experienceRequired,
                levelExperience: this.levelExperience,
                vacancyActive: true
            }, { timeout: 5000 })
                .then(response => {
                    window.location.reload()
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

        loadDataVacancies() {
            this.idURL = new URL(window.location).searchParams.get("id");
            axios.get(`/api/jobvacancy`)
                .then((res) => {
                    this.jobVacancy = res.data
                    console.log(this.jobVacancy)
                })
            axios.get(`/api/jobvacancy/${this.idURL}`)
                .then((res) => {
                    this.vacancies = res.data;
                });
        },

        changeEditVacancies(vacancy) {
            this.id = vacancy.id;
            this.jobVacancyTitle = vacancy.jobVacancyTitle;
            this.jobVacancyDescription = vacancy.jobVacancyDescription;
            this.branchOffice = vacancy.branchOffice;
            this.levelExperience = vacancy.levelExperience;
            this.experienceRequired = vacancy.experienceRequired;
            this.jobVacancySkills = vacancy.jobVacancySkills;
        },

        editVacancy() {
            axios.patch(`/api/jobvacancy/${this.id}`,
                {
                    jobVacancyTitle: this.jobVacancyTitle,
                    jobVacancyDescription: this.jobVacancyDescription,
                    jobVacancySkills: this.jobVacancySkills,
                    branchOffice: this.branchOffice,
                    experienceRequired: this.experienceRequired,
                    levelExperience: this.levelExperience,
                    vacancyActive: this.vacancyActive
                })
                .then((response) => window.location.reload())
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


        clearFilters() {
            this.filterSelectGender = null;
            this.filterSelectCivilStatus = null;
            this.filterSelectChildren = null;
            this.filterSelectSalary = null;
            this.filterSelectTitle = null;
            this.filterSelectBranchOffice = null;
            this.filterSelectRecruiterRol = null;
        },


        onModalOpen(applicationId) {
            this.selectedApplicationId = applicationId;
        },

        /* Users data */
        loadDataUsers() {

            axios.get(`/api/users/seekers`)
                .then((res) => {
                    this.allData = res.data;
                    this.filePDF = this.allData[0].files;
                    this.dataEducations = this.allData[0].educations;
                    this.titleUsers = this.dataEducations.map(p => p.title);
                    this.filterSeekers = this.allData;
                })
        },

        getJobVacancy() {
            axios.get(`/api/jobapplication/${this.idURL}`)
                .then((res) => {
                    this.seekerApplications = res.data;
                })
                .catch((error) => {
                    this.error = error.response.data;
                })
        },

        updateApplicationState() {
            const modal = document.querySelector('#stateApplicationModal');
            const modalInstance = bootstrap.Modal.getInstance(modal);
            modalInstance.hide();
            Swal.fire({
                title: '¿Estás seguro?',
                text: 'Esta acción cambiará el estado de la solicitud de empleo',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Sí, cambiar estado',
                cancelButtonText: 'Cancelar',
                confirmButtonColor: '#198754',
            }).then((result) => {
                if (result.isConfirmed) {
                    const toast = Swal.fire({
                        title: 'Actualizando estado...',
                        toast: true,
                        position: 'top-end',
                        showConfirmButton: false,
                        timerProgressBar: true,
                        timer: 3000
                    });
                    axios.patch(`/api/jobapplication/state/${this.selectedApplicationId}`, {
                        stateApplication: this.selectedState
                    }).then(response => {
                        const updatedJobApplication = response.data;
                        if (this.jobApplications) {
                            const index = this.jobApplications.findIndex(jobApplication => jobApplication.id === updatedJobApplication.id);
                            if (index >= 0) {
                                this.jobApplications.splice(index, 1, updatedJobApplication);
                            }
                        }
                        modalInstance.hide();
                        window.location.reload();
                    }).catch(error => {
                        toast.close();
                        this.error = error.response.data;
                        Swal.fire({
                            icon: 'Error',
                            title: 'Error',
                            text: this.error,
                            confirmButtonColor: "#DB504A"
                        })
                    });
                } else {
                    modalInstance.show();
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


    computed: {

        filterSelect() {
            const filterObject = {};
            if (this.filterSelectGender) {
                filterObject.gender = this.filterSelectGender;
            }
            if (this.filterSelectCivilStatus) {
                filterObject.civilStatus = this.filterSelectCivilStatus;
            }
            if (this.filterSelectChildren) {
                filterObject.children = this.filterSelectChildren;
            }
            if (this.filterSelectSalary) {
                filterObject.salary = this.filterSelectSalary;
            }
            if (this.filterSelectTitle) {
                filterObject.title = this.filterSelectTitle;
            }
            if (this.message !== '') {
                const lowerCaseMessage = this.message.toLowerCase();
                filterObject.nameOrEmailOrDocOrState = user => {
                    const lowerCaseMessage = this.message.toLowerCase();

                    if (user.firstName.toLowerCase().includes(lowerCaseMessage)) {
                        return true;
                    }
                    if (user.lastName.toLowerCase().includes(lowerCaseMessage)) {
                        return true;
                    }
                    if (user.email.toLowerCase().includes(lowerCaseMessage)) {
                        return true;
                    }
                    if (user.personalData) {
                        const personalDataValues = Object.values(user.personalData);
                        for (const value of personalDataValues) {
                            if (value.document.toLowerCase().includes(lowerCaseMessage)) {
                                return true;
                            }
                            if (value.stateResidence.toLowerCase().includes(lowerCaseMessage)) {
                                return true;
                            }
                        }
                    }

                    return false;
                };

            }
            return this.allData.filter(user => {
                for (const [key, value] of Object.entries(filterObject)) {
                    if (key === 'nameOrEmailOrDocOrState') {
                        if (!value(user)) {
                            return false;
                        }
                    } else {
                        const userValues = user.personalData.map(p => p[key]).concat(user.otherDatas.map(o => o[key]), user.educations.map(e => e[key]));
                        if (!userValues.includes(value)) {
                            return false;
                        }
                    }
                }
                return true;
            });
        },

        filterSelectVacancies() {
            const filterObject = {};
            if (this.filterSelectBranchOffice) {
                filterObject.branchOffice = this.filterSelectBranchOffice;
            }
            if (this.filterSelectRecruiterRol) {
                filterObject.postedBy = this.filterSelectRecruiterRol;
            }
            if (this.message !== '') {
                const lowerCaseMessage = this.message.toLowerCase();
                filterObject.branchOfficeOrRecruiterRol = vacancy => {
                    const lowerCaseMessage = this.message.toLowerCase();
                    if (vacancy.jobVacancyTitle.toLowerCase().includes(lowerCaseMessage)) {
                        return true;
                    }
                    return false;
                };

            }

            return this.jobVacancy.filter(vacancy => {
                for (const [key, value] of Object.entries(filterObject)) {
                    if (key === 'branchOfficeOrRecruiterRol') {
                        if (!value(vacancy)) {
                            return false;
                        }
                    } else {
                        if (vacancy[key] !== value) {
                            return false;
                        }
                    }
                }
                return true;
            });
        },

        filterSeekersApplication() {
            const filterObject = {};
            if (this.filterSelectGender) {
                filterObject.gender = this.filterSelectGender;
            }
            if (this.filterSelectCivilStatus) {
                filterObject.civilStatus = this.filterSelectCivilStatus;
            }
            if (this.filterSelectChildren) {
                filterObject.children = this.filterSelectChildren;
            }
            if (this.filterSelectSalary) {
                filterObject.salary = this.filterSelectSalary;
            }
            if (this.filterSelectTitle) {
                filterObject.title = this.filterSelectTitle;
            }
            if (this.message !== '') {
                const lowerCaseMessage = this.message.toLowerCase();
                console.log('Valor de búsqueda:', lowerCaseMessage);
                filterObject.nameOrEmailOrDocOrState = user => (
                    user.seeker && user.seeker.personalData && user.seeker.personalData.length > 0 &&
                    user.seeker.personalData[0].firstName &&
                    user.seeker.personalData[0].firstName.toLowerCase().includes(lowerCaseMessage) ||
                    user.seeker.personalData[0].lastName &&
                    user.seeker.personalData[0].lastName.toLowerCase().includes(lowerCaseMessage) ||
                    user.seeker.personalData[0].email &&
                    user.seeker.personalData[0].email.toLowerCase().includes(lowerCaseMessage) ||
                    user.seeker.personalData[0].document &&
                    user.seeker.personalData[0].document.toLowerCase().includes(lowerCaseMessage) ||
                    user.seeker.personalData[0].stateResidence &&
                    user.seeker.personalData[0].stateResidence.toLowerCase().includes(lowerCaseMessage)
                );
            }
            const filteredApplications = [...this.seekerApplications];
            return this.seekerApplications.filter(user => {
                for (const [key, value] of Object.entries(filterObject)) {
                    console.log(filterObject);
                    if (key === 'nameOrEmailOrDocOrState') {
                        if (!value(user)) {
                            return false;
                        }
                        console.log(filterObject);
                    } else if (key === 'educations') {
                        const userValues = user.seeker.educations.map(e => e[value]);
                        console.log(userValues);
                        if (!userValues.includes(value)) {
                            return false;
                        }
                        console.log(filterObject);
                    } else {
                        const userValues = user.seeker.personalData.map(p => p[key]).concat(user.seeker.otherDatas.map(o => o[key]));
                        console.log(userValues);
                        if (!userValues.includes(value)) {
                            return false;
                        }
                        console.log(filterObject);
                    }
                }
                return true;
            });
        },

        searchRecruiters() {
            return this.recruitersData.filter(item => {
                if (this.messageRecruiter !== '') {
                    return item.firstName.toLowerCase().includes(this.messageRecruiter.toLowerCase())
                        || item.lastName.toLowerCase().includes(this.messageRecruiter.toLowerCase())
                        || item.email.toLowerCase().includes(this.messageRecruiter.toLowerCase())
                } else {
                    this.filterSeekers = this.recruitersData
                }
                return true;
            })
        },

        searchVacanciesR() {
            return this.vacancyRecruiter.filter(item => {
                return item.jobVacancyTitle.toLowerCase().includes(this.messageVacanciesRecruiter.toLowerCase())
            })
        },


        portalLink() {
            return this.isLoggedIn ? "myhome.html" : "./login.html";
        },

        portalText() {
            return this.isLoggedIn ? "Mi portal Garzón" : "Portal Web";
        },
    },
}).mount("#app");

