const { createApp } = Vue;

createApp({
  data() {
    return {
      firstName: '',
      lastName: '',
      email: '',
      branchCategory: '',
      branchOffice: '',
      recruiter: '',
      newItems: {
        description: '',
        category: []
      },
      itemsCategory: '',
      name: '',
      addItemsCategory: '',
      roleCategory: [],
      showButtonsSection: true,
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
      addItemsCategory: [],
      showSection: false,
      showFirst: false,
      showSecond: false,
      showThree: false,
      showFour: false,
      showFive: false,
      showSix: false,
      showSeven: false,
      showEigth: false,
      showNine: false,
      showTen: false,
      showAddItemStatus: false,
      showUpdateItemStatus: false,
      showDeleteItemStatus: false,
      showForm: false,
      showFooter: false,
      showResetButton: false,
      showFirstItem: true,
      showSecondItem: true,
      showButtons: true,
      showThreeItem: true,
      showFourItem: true,
      showFiveItem: true,
      showSixItem: true,
      showSevenItem: true,
      showEigthItem: true,
      showNineItem: true,
      showTenItem: true,
      showButtonUpdate: true,
      maritalStatusId: '',
      description: '',
      captureMaritalStatusId: '',
      gender: '',
      civilStatus: '',
      genderId: [],
      maritalId: [],
      typeStudyId: [],
      areaStudyId: [],
      stateStudyId: [],
      activityCompanyId: [],
      genderIds: [],
      maritalIds: [],
      typeStudyIds: [],
      areaStudyIds: [],
      stateStudyIds: [],
      activityCompanyIds: [],
      genderSelectId: '',
      civilStatusSelectId: '',
      typeStudySelectId: '',
      areaStudySelectId: '',
      stateStudySelectId: '',
      experienceLevelCategoryId: '',
      langueguesSelectId: '',
      experienceSelectId: '',
      AspiresSelectId: '',
      salarySelectId: '',
      nivelLSelectId: '',
      transportSelectId: '',
      reasonSelectId: '',
      avaivalitySelectId: '',
      typeDSelectId: '',
      activeCategory: '',
      typeJobSelectId: '',
      branchOfficeSelectId: '',
      statePSelectId: '',
      typeRelashionSelectId: '',
      activitySelectId: '',
      rols: [],
      rol: '',
      itemActive: [],
      selectedCategories: [],
      nameCategory: [],
      inactiveCategories: [],
      inactiveRecruiters: [],
      recruitersSelectId: null,
      adminSelectId: null,
      showButtonUpdateAdmin: false,
      showButtonUpdateRecruiter: false,
      inactiveRecruiterid: [],
      inactiveAdmins: [],
      activateUsers: [],
      inactiveRecruiterSelected: [],
      inactiveRecruiterlected: [],
      showButtons: true,
      showResetButton: true,
      isAdminSectionVisible: false,
      isRecruiterSectionVisible: false,
      showAdminButton: true,
      showRecruiterButton: true,
      inactiveAdminSelected: [],
      selectedRecruiterId: [],
      showAddItemStatusAdmin: false,
      showAddItemStatusRecruiter: false

    };
  },

  created() {

    const userSession = localStorage.getItem("userSession");
    if (userSession) {
      this.isLoggedIn = true;
    }

    this.enumsPersonalData();
    this.getItemsCategory();
    this.getItems();
    this.getRecruiters();
    this.getAdmins();

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

    enumsPersonalData() {

      axios.get("/api/enum/yesorno").then((res) => {
        this.yesOrNo = res.data;
      })

      axios.get("/api/enum/rol").then((res) => {
        this.rols = res.data;
      })

    },

    getItemsCategory() {
      axios.get("/api/items/categories")
        .then(response => {
          this.itemsCategory = response.data;
          this.nameCategory = this.itemsCategory.map(category => category.name);
          this.genderCategory = this.itemsCategory.filter(category => category.name === "Genero");
          this.genderId = this.genderCategory[0].id;
          this.genderCategory = Object.keys(this.genderCategory[0].itemsCatalogueList).map(key => this.genderCategory[0].itemsCatalogueList[key]);
          this.inactiveGenderCategory = this.genderCategory.filter(category => category.activeItems === false)
          this.genderCategory = this.genderCategory.filter(category => category.activeItems === true).sort((a, b) => {
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
          this.inactiveMaritalStatusCategory = this.maritalStatusCategory.filter(category => category.activeItems === false);
          this.maritalStatusCategory = this.maritalStatusCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

        this.educationStatusCategory = this.itemsCategory.filter(category => category.name === "Estado/educación");
          this.stateStudyId = this.educationStatusCategory[0].id;
          this.educationStatusCategory = Object.keys(this.educationStatusCategory[0].itemsCatalogueList).map(key => this.educationStatusCategory[0].itemsCatalogueList[key])
          this.inactiveEducationStatusCategory = this.educationStatusCategory.filter(category => category.activeItems === false);
          this.educationStatusCategory = this.educationStatusCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

        this.experienceLevelCategory = this.itemsCategory.filter(category => category.name === "Nivel de experiencia");
          this.experienceLevelCategoryId = this.experienceLevelCategory[0].id;
          this.experienceLevelCategory = Object.keys(this.experienceLevelCategory[0].itemsCatalogueList).map(key => this.experienceLevelCategory[0].itemsCatalogueList[key])
          this.inactiveExperienceLevelCategory = this.experienceLevelCategory.filter(category => category.activeItems === false);
          this.experienceLevelCategory = this.experienceLevelCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

        this.workAvailabilityCategory = this.itemsCategory.filter(category => category.name === "Disponibilidad horaria");
          this.workAvailabilityCategoryId = this.workAvailabilityCategory[0].id;
          this.workAvailabilityCategory = Object.keys(this.workAvailabilityCategory[0].itemsCatalogueList).map(key => this.workAvailabilityCategory[0].itemsCatalogueList[key])
          this.inactiveWorkAvailabilityCategory = this.workAvailabilityCategory.filter(category => category.activeItems === false);
          this.workAvailabilityCategory = this.workAvailabilityCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

          this.applicationStatusCategory = this.itemsCategory.filter(category => category.name === "Estado/postulación");
          this.applicationStatusCategoryId = this.applicationStatusCategory[0].id;
          this.applicationStatusCategory = Object.keys(this.applicationStatusCategory[0].itemsCatalogueList).map(key => this.applicationStatusCategory[0].itemsCatalogueList[key])
          this.inactiveApplicationStatusCategory = this.applicationStatusCategory.filter(category => category.activeItems === false);
          this.applicationStatusCategory = this.applicationStatusCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

          this.applicationStateCategory = this.itemsCategory.filter(category => category.name === "Estado/Aplicación");
          this.applicationStateCategoryId = this.applicationStateCategory[0].id;
          this.applicationStateCategory = Object.keys(this.applicationStateCategory[0].itemsCatalogueList).map(key => this.applicationStateCategory[0].itemsCatalogueList[key])
          this.inactiveApplicationStateCategory = this.applicationStateCategory.filter(category => category.activeItems === false);
          this.applicationStateCategory = this.applicationStateCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

        this.languageCategory = this.itemsCategory.filter(category => category.name === "Lenguajes");
          this.languageCategoryId = this.languageCategory[0].id;
          this.languageCategory = Object.keys(this.languageCategory[0].itemsCatalogueList).map(key => this.languageCategory[0].itemsCatalogueList[key])
          this.inactiveLanguageCategory = this.languageCategory.filter(category => category.activeItems === false);
          this.languageCategory = this.languageCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

          this.languageLevelCategory = this.itemsCategory.filter(category => category.name === "Nivel del idioma");
          this.languageLevelCategoryId = this.languageLevelCategory[0].id;
          this.languageLevelCategory = Object.keys(this.languageLevelCategory[0].itemsCatalogueList).map(key => this.languageLevelCategory[0].itemsCatalogueList[key])
          this.inactiveLanguageLevelCategory = this.languageLevelCategory.filter(category => category.activeItems === false);
          this.languageLevelCategory = this.languageLevelCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

          this.desiredPositionCategory = this.itemsCategory.filter(category => category.name === "Puesto al que aspira");
          this.desiredPositionCategoryId = this.desiredPositionCategory[0].id;
          this.desiredPositionCategory = Object.keys(this.desiredPositionCategory[0].itemsCatalogueList).map(key => this.desiredPositionCategory[0].itemsCatalogueList[key])
          this.inactiveDesiredPositionCategory = this.desiredPositionCategory.filter(category => category.activeItems === false);
          this.desiredPositionCategory = this.desiredPositionCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

        this.retirementReasonCategory = this.itemsCategory.filter(category => category.name === "Razón del retiro");
        this.retirementReasonCategoryId = this.retirementReasonCategory[0].id;
        this.retirementReasonCategory = Object.keys(this.retirementReasonCategory[0].itemsCatalogueList).map(key => this.retirementReasonCategory[0].itemsCatalogueList[key])
        this.inactiveRetirementReasonCategory = this.retirementReasonCategory.filter(category => category.activeItems === false);
        this.retirementReasonCategory = this.retirementReasonCategory.filter(category => category.activeItems === true).sort((a, b) => {
          if (a.description < b.description) {
              return -1;
          }
          if (a.description > b.description) {
              return 1;
            }
            return 0;
      })

        this.recruiterRoleCategory = this.itemsCategory.filter(category => category.name === "Rol");
        this.recruiterRoleCategoryId = this.recruiterRoleCategory[0].id;
        this.recruiterRoleCategory = Object.keys(this.recruiterRoleCategory[0].itemsCatalogueList).map(key => this.recruiterRoleCategory[0].itemsCatalogueList[key])
        this.inactiveRecruiterRoleCategory = this.recruiterRoleCategory.filter(category => category.activeItems === true);
        this.recruiterRoleCategory = this.recruiterRoleCategory.filter(category => category.activeItems === true).sort((a, b) => {
          if (a.description < b.description) {
              return -1;
          }
          if (a.description > b.description) {
              return 1;
            }
            return 0;
      })

        this.personalRelationshipCategory = this.itemsCategory.filter(category => category.name === "Relaciones/personal");
        this.personalRelationshipCategoryId = this.personalRelationshipCategory[0].id;
        this.personalRelationshipCategory = Object.keys(this.personalRelationshipCategory[0].itemsCatalogueList).map(key => this.personalRelationshipCategory[0].itemsCatalogueList[key])
        this.inactivePersonalRelationshipCategory = this.personalRelationshipCategory.filter(category => category.activeItems === false);
        this.personalRelationshipCategory = this.personalRelationshipCategory.filter(category => category.activeItems === true).sort((a, b) => {
          if (a.description < b.description) {
              return -1;
          }
          if (a.description > b.description) {
              return 1;
            }
            return 0;
      })

      this.salaryRangeCategory = this.itemsCategory.filter(category => category.name === "Rango de salario");
          this.salaryRangeCategoryId = this.salaryRangeCategory[0].id;
          this.salaryRangeCategory = Object.keys(this.salaryRangeCategory[0].itemsCatalogueList).map(key => this.salaryRangeCategory[0].itemsCatalogueList[key])
          this.inactiveSalaryRangeCategory = this.salaryRangeCategory.filter(category => category.activeItems === false);
          this.salaryRangeCategory = this.salaryRangeCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

          this.transportTypeCategory = this.itemsCategory.filter(category => category.name === "Tipo de transporte");
          this.transportTypeCategoryId = this.transportTypeCategory[0].id;
          this.transportTypeCategory = Object.keys(this.transportTypeCategory[0].itemsCatalogueList).map(key => this.transportTypeCategory[0].itemsCatalogueList[key])
          this.inactiveTransportTypeCategory = this.transportTypeCategory.filter(category => category.activeItems === false);
          this.transportTypeCategory = this.transportTypeCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

          this.branchCategory = this.itemsCategory.filter(category => category.name === "Sucursales");
          this.branchCategoryId = this.branchCategory[0].id;
          this.branchCategory = Object.keys(this.branchCategory[0].itemsCatalogueList).map(key => this.branchCategory[0].itemsCatalogueList[key])
          this.inactiveBranchCategory = this.branchCategory.filter(category => category.activeItems === false);
          this.branchCategory = this.branchCategory.filter(category => category.activeItems === true).sort((a, b) => {
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
          this.inactiveDisabilityTypeCategory = this.disabilityTypeCategory.filter(category => category.activeItems === false);
          this.disabilityTypeCategory = this.disabilityTypeCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

          this.jobTypeCategory = this.itemsCategory.filter(category => category.name === "Tipo de trabajo");
          this.jobTypeCategoryId = this.jobTypeCategory[0].id;
          this.jobTypeCategory = Object.keys(this.jobTypeCategory[0].itemsCatalogueList).map(key => this.jobTypeCategory[0].itemsCatalogueList[key])
          this.inactivejobTypeCategory = this.jobTypeCategory.filter(category => category.activeItems === false);
          this.jobTypeCategory = this.jobTypeCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

          this.studyTypeCategory = this.itemsCategory.filter(category => category.name === "Tipo de estudio");
          this.studyTypeCategoryId = this.studyTypeCategory[0].id;
          this.studyTypeCategory = Object.keys(this.studyTypeCategory[0].itemsCatalogueList).map(key => this.studyTypeCategory[0].itemsCatalogueList[key])
          this.inactiveStudyTypeCategory = this.studyTypeCategory.filter(category => category.activeItems === false);
          this.studyTypeCategory = this.studyTypeCategory.filter(category => category.activeItems === true).sort((a, b) => {
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
          this.inactiveStudyTypeOptions = this.studyTypeCategory.filter(category => category.activeItems === false);
          this.studyTypeCategory = this.studyTypeCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })

          this.activityCategory = this.itemsCategory.filter(category => category.name === "Actividad de la Compañia");
          this.activityCompanyId = this.activityCategory[0].id;
          const mappedActivityCategory = Object.keys(this.activityCategory[0].itemsCatalogueList).map(key => this.activityCategory[0].itemsCatalogueList[key])
          this.activityCategory = mappedActivityCategory;
          this.inactiveActivityCategory = this.activeCategory.filter(category => category.activeItems === false);
          this.activityCategory = this.activityCategory.filter(category => category.activeItems === true).sort((a, b) => {
            if (a.description < b.description) {
                return -1;
            }
            if (a.description > b.description) {
                return 1;
              }
              return 0;
        })
        })
        .catch(error => {
          this.error = error.response.data;
        })
    },

    showFirstSection() {
      this.showFirst = true;
      this.showSecond = false;
      this.showAddItemStatus = false;
      this.showThree = false;
      this.showFooter = false;
    },

    showSecondSection() {
      this.showFirst = false;
      this.showSecond = true;
      this.showAddItemStatus = false;
      this.showThree = false;
    },

    showThreeSection() {
      this.showFirst = false;
      this.showSecond = false;
      this.showThree = true;
      this.showAddItemStatus = false;
    },

    showAddItem() {
      this.showButtons = false;
      this.showUpdateItemStatus = false;
      this.showDeleteItemStatus = false;
      this.setActiveCategory(this.activeCategory);
      this.showAddItemStatus = true;
      this.showFirst = false;
      this.showSecond = false;
      this.showThree = false;
      this.showFour = false;
      this.showFooter = false;
    
      if (this.activeCategory === "admin") {
        this.showAddItemStatusAdmin = true;
      } else if (this.activeCategory === "recruiter") {
        this.showAddItemStatusRecruiter = true;
      }
    },

    showUpdateItem() {
      this.showButtons = false;
      this.showFirst = false;
      this.showSecond = false;
      this.showThree = false;
      this.showFour = false;
      this.showFive = false;
      this.showSix = false;
      this.showSeven = false;
      this.showEigth = false;
      this.showNine = false;
      this.showTen = false;
      this.showDeleteItemStatus = false;
      this.showAddItemStatus = false;
      this.showUpdateItemStatus = true;
      this.showFooter = false;
      if (this.activeCategory === "Genero" || this.activeCategory === "tipo de estudio" || this.activeCategory === "Actividad de la empresa" || this.activeCategory === "idiomas" || this.activeCategory === 'puesto' || this.activeCategory === "admin") {
        this.showFirst = true;
      } else if (this.activeCategory === "Estado civil" || this.activeCategory === "Estado" || this.activeCategory === "Nivel de experiencia" || this.activeCategory === "salario" || this.activeCategory === "niveles" || this.activeCategory === "recruiter") {
        this.showSecond = true;
      } else if (this.activeCategory === 'transporte') {
        this.showThree = true;
      } else if (this.activeCategory === "sucursal") {
        this.showFour = true;
      } else if (this.activeCategory === "razon") {
        this.showFive = true;
      } else if (this.activeCategory === "tipo de discapacidad") {
        this.showSix = true;
      } else if (this.activeCategory === "disponibilidad") {
        this.showSeven = true;
      } else if (this.activeCategory === "tipo de trabajo") {
        this.showEigth = true;
      } else if (this.activeCategory === "tipo de relacion") {
        this.showNine = true;
      } else if (this.activeCategory === "estado de la postulacion") {
        this.showTen = true;
      }
    },

    showDeleteItem() {
      this.showButtons = false;
      this.showFirst = false;
      this.showSecond = false;
      this.showThree = false;
      this.showFour = false;
      this.showFive = false;
      this.showSix = false;
      this.showSeven = false;
      this.showEigth = false;
      this.showNine = false;
      this.showTen = false;
      this.showDeleteItemStatus = true;
      this.showAddItemStatus = false;
      this.showUpdateItemStatus = false;
      this.showFooter = false;
      if (this.activeCategory === "Genero" || this.activeCategory === "tipo de estudio" || this.activeCategory === "Actividad de la empresa" || this.activeCategory === "idiomas" || this.activeCategory === "puesto" || this.activeCategory === "admin") {
        this.showFirst = true;
      } else if (this.activeCategory === "Estado civil" || this.activeCategory === "Estado" || this.activeCategory === "Nivel de experiencia" || this.activeCategory === "salario" || this.activeCategory === "niveles" || this.activeCategory === "recruiter") {
        this.showSecond = true;
      } else if (this.activeCategory === 'transporte') {
        this.showThree = true;
      } else if (this.activeCategory === "sucursal") {
        this.showFour = true;
      } else if (this.activeCategory === "razon") {
        this.showFive = true;
      } else if (this.activeCategory === "tipo de discapacidad") {
        this.showSix = true;
      } else if (this.activeCategory === "disponibilidad") {
        this.showSeven = true;
      } else if (this.activeCategory === "tipo de trabajo") {
        this.showEigth = true;
      } else if (this.activeCategory === "tipo de relacion") {
        this.showNine = true;
      } else if (this.activeCategory === "estado de la postulacion") {
        this.showTen = true;
      }
    },

    showActiveItem() {
      this.showButtons = false;
      this.showFirst = false;
      this.showSecond = false;
      this.showThree = false;
      this.showFour = false;
      this.showFive = false;
      this.showSix = false;
      this.showSeven = false;
      this.showEigth = false;
      this.showNine = false;
      this.showTen = false;
      this.showDeleteItemStatus = false;
      this.showAddItemStatus = false;
      this.showUpdateItemStatus = false;
      this.showFooter = false;
      this.showActiveItemStatus = true;
      if (this.activeCategory === "Genero" || this.activeCategory === "tipo de estudio" || this.activeCategory === "Actividad de la empresa" || this.activeCategory === "idiomas" || this.activeCategory === "puesto" || this.activeCategory === "admin") {
        this.showFirst = true;
      } else if (this.activeCategory === "Estado civil" || this.activeCategory === "Estado" || this.activeCategory === "Nivel de experiencia" || this.activeCategory === "salario" || this.activeCategory === "niveles" || this.activeCategory === "recruiter") {
        this.showSecond = true;
      } else if (this.activeCategory === 'transporte') {
        this.showThree = true;
      } else if (this.activeCategory === "sucursal") {
        this.showFour = true;
      } else if (this.activeCategory === "razon") {
        this.showFive = true;
      } else if (this.activeCategory === "tipo de discapacidad") {
        this.showSix = true;
      } else if (this.activeCategory === "disponibilidad") {
        this.showSeven = true;
      } else if (this.activeCategory === "tipo de trabajo") {
        this.showEigth = true;
      } else if (this.activeCategory === "tipo de relacion") {
        this.showNine = true;
      } else if (this.activeCategory === "estado de la postulacion") {
        this.showTen = true;
      }
    },

    activeAdmin() {
      this.isAdminSectionVisible = true;
      this.isRecruiterSectionVisible = false;
      this.showAdminButton = false;
      this.showRecruiterButton = false;
      this.showResetButton = true;
    },

    activeRecruiter() {
      this.isAdminSectionVisible = false;
      this.isRecruiterSectionVisible = true;
      this.showAdminButton = false;
      this.showRecruiterButton = false;
      this.showResetButton = true;
    },

    setActiveCategory(category) {
      this.activeCategory = category;
      this.showResetButton = true;
      this.showFooter = true;
      this.showFirstItem =
        this.activeCategory === "Genero" ||
        this.activeCategory === "tipo de estudio" ||
        this.activeCategory === "Actividad de la empresa" ||
        this.activeCategory === "idiomas" ||
        this.activeCategory === "puesto" ||
        this.activeCategory === "admin";
      this.showSecondItem =
        this.activeCategory === "Estado civil" ||
        this.activeCategory === "Estado" ||
        this.activeCategory === "Nivel de experiencia" ||
        this.activeCategory === "salario" ||
        this.activeCategory === "niveles" ||
        this.activeCategory === "recruiter";
      this.showThreeItem = this.activeCategory === "transporte";
      this.showFourItem = this.activeCategory === "sucursal";
      this.showFiveItem = this.activeCategory === "razon";
      this.showSixItem = this.activeCategory === "tipo de discapacidad";
      this.showSevenItem = this.activeCategory === "disponibilidad";
      this.showEigthItem = this.activeCategory === "tipo de trabajo";
      this.showNineItem = this.activeCategory === "tipo de relacion";
      this.showTenItem = this.activeCategory === "estado de la postulacion";

      if (category === 'admin') {
        this.showButtonUpdateAdmin = true;
        this.showButtonUpdateRecruiter = false;
      } else if (category === 'recruiter') {
        this.showButtonUpdateAdmin = false;
        this.showButtonUpdateRecruiter = true;
      }
    },

    resetForm() {
      this.activeCategory = '';
      this.showButtons = true;
      this.showResetButton = false;
      this.showFirstItem = true;
      this.showSecondItem = true;
      this.showThreeItem = true;
      this.showFourItem = true;
      this.showFiveItem = true;
      this.showSixItem = true;
      this.showSevenItem = true;
      this.showEigthItem = true;
      this.showNineItem = true;
      this.showTenItem = true;
      this.showFirst = false;
      this.showSecond = false;
      this.showUpdateItemStatus = false;
      this.showAddItemStatus = false;
      this.showDeleteItemStatus = false;
      this.showActiveItemStatus = false;
      this.showFooter = false;
      this.inactiveAdminSelected = [];
      this.inactiveRecruiterSelected = [];
      this.isAdminSectionVisible = false;
      this.showAdminButton = true;
      this.showRecruiterButton = true;
      this.isRecruiterSectionVisible = false;
      this.showAddItemStatusAdmin = false;
      this.showAddItemStatusRecruiter = false;
    },

    submitForm() {
      if (this.activeCategory === "Genero" && this.genderId) {
        this.submitFormCategory(this.genderId);
      } else if (this.activeCategory === "Estado civil" && this.maritalId) {
        this.submitFormCategory(this.maritalId);
      } else if (this.activeCategory === "tipo de estudio" && this.typeStudyId) {
        this.submitFormCategory(this.typeStudyId);
      } else if (this.activeCategory === "Actividad de la empresa" && this.activityCompanyId) {
        this.submitFormCategory(this.activityCompanyId);
      } else if (this.activeCategory === "Estado" && this.stateStudyId) {
        this.submitFormCategory(this.stateStudyId);
      } else if (this.activeCategory === "Nivel de experiencia" && this.experienceLevelCategoryId) {
        this.submitFormCategory(this.experienceLevelCategoryId);
      } else if (this.activeCategory === "puesto" && this.desiredPositionCategoryId) {
        this.submitFormCategory(this.desiredPositionCategoryId);
      } else if (this.activeCategory === "salario" && this.salaryRangeCategoryId) {
        this.submitFormCategory(this.salaryRangeCategoryId)
      } else if (this.activeCategory === "transporte" && this.transportTypeCategoryId) {
        this.submitFormCategory(this.transportTypeCategoryId)
      } else if (this.activeCategory === "sucursal" && this.branchCategoryId) {
        this.submitFormCategory(this.branchCategoryId)
      } else if (this.activeCategory === "razon" && this.retirementReasonCategoryId) {
        this.submitFormCategory(this.retirementReasonCategoryId)
      } else if (this.activeCategory === "tipo de discapacidad" && this.disabilityTypeCategoryId) {
        this.submitFormCategory(this.disabilityTypeCategoryId)
      } else if (this.activeCategory === "disponibilidad" && this.workAvailabilityCategoryId) {
        this.submitFormCategory(this.workAvailabilityCategoryId)
      } else if (this.activeCategory === "tipo de trabajo" && this.jobTypeCategoryId) {
        this.submitFormCategory(this.jobTypeCategoryId)
      } else if (this.activeCategory === "tipo de relacion" && this.personalRelationshipCategoryId) {
        this.submitFormCategory(this.personalRelationshipCategoryId)
      } else if (this.activeCategory === "estado de la postulacion" && this.applicationStateCategoryId) {
        this.submitFormCategory(this.applicationStateCategoryId)
      } else if (this.activeCategory === "idiomas" && this.languageCategoryId) {
        this.submitFormCategory(this.languageCategoryId)
      } else if (this.activeCategory === "niveles" && this.languageLevelCategoryId) {
        this.submitFormCategory(this.languageLevelCategoryId)
      }
    },

    getActiveCategoryDescriptions() {
      switch (this.activeCategory) {
        case 'idiomas':
          return this.languageDescriptions;
        case 'niveles':
          return this.levelDescriptions;
        case 'Genero':
          return this.genderDescriptions;
        case 'Estado Civil':
          return this.maritalStatusDescriptions;
        case 'tipo de Estudio':
          return this.studyTypeDescriptions;
        case 'Actividad':
          return this.activityDescriptions;
        case 'Estado':
          return this.stateDescriptions;
        case 'Nivel de experiencia':
          return this.experienceLevelDescriptions;
        case 'puesto':
          return this.positionDescriptions;
        case 'salario':
          return this.salaryRangeDescriptions;
        case 'transporte':
          return this.transportTypeDescriptions;
        case 'sucursal':
          return this.branchOfficeDescriptions;
        case 'razon':
          return this.reasonDescriptions;
        case 'tipo de discapacidad':
          return this.disabilityTypeDescriptions;
        case 'disponibilidad':
          return this.workAvailabilityDescriptions;
        case 'tipo de trabajo':
          return this.jobTypeDescriptions;
        case 'tipo de relacion':
          return this.personalRelationshipDescriptions;
        case 'tipo de identificacion':
          return this.identificationTypeDescriptions;
        default:
          return [];
      }
    },

    checkIfDescriptionExists() {
      const existingDescriptions = this.getActiveCategoryDescriptions();

      if (existingDescriptions && Array.isArray(existingDescriptions)) {
        return existingDescriptions.includes(this.description);
      }
      return false;
    },


    submitFormCategory(id) {
      const isDescriptionDuplicate = this.checkIfDescriptionExists();
      if (isDescriptionDuplicate) {
        console.log('Descripción duplicada');
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'El item ya se encuentra registrado'
        });
        return;
      }

      Swal.fire({
        text: '¿Estás seguro de agregar el item?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          axios.post(`/api/item/${id}`, {
            description: this.description
          })
            .then(response => {
              console.log('Respuesta del backend:', response);
              Swal.fire({
                icon: 'success',
                title: 'Item agregado con exito',
                showConfirmButton: false,
              });
              setTimeout(() => {
                window.location.reload();
              }, 2000);
            })
            .catch(error => {
              console.log('Error en la solicitud POST:', error);
              this.error = error.response.data;
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: this.error,
                confirmButtonColor: "#DB504A"
              });
            });
        }
      });
    },

    updateForm() {
      const itemsMap = new Map([
        [this.langueguesSelectId, 'idiomas'],
        [this.nivelLSelectId, 'niveles'],
        [this.genderSelectId, 'Genero'],
        [this.civilStatusSelectId, 'Estado Civil'],
        [this.typeStudySelectId, 'tipo de Estudio'],
        [this.activitySelectId, 'Actividad'],
        [this.stateStudySelectId, 'Estado'],
        [this.experienceSelectId, 'Nivel de experiencia'],
        [this.AspiresSelectId, 'puesto'],
        [this.salarySelectId, 'salario'],
        [this.transportSelectId, 'transporte'],
        [this.branchOfficeSelectId, 'sucursal'],
        [this.reasonSelectId, 'razon'],
        [this.typeDSelectId, 'tipo de discapacidad'],
        [this.avaivalitySelectId, 'disponibilidad'],
        [this.typeJobSelectId, 'tipo de trabajo'],
        [this.typeRelashionSelectId, 'tipo de relacion'],
        [this.statePSelectId, 'estado de la postulacion']
      ]);

      let id = '';
      let itemName = '';

      for (const [itemId, itemName] of itemsMap) {
        if (itemId) {
          id = itemId;
          break;
        }
      }

      if (!id) {
        return;
      }

      const isDescriptionDuplicate = this.checkIfDescriptionExists(this.description);
      if (isDescriptionDuplicate) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'El item ya se encuentra registrado'
        });
        return;
      }

      Swal.fire({
        text: '¿Estás seguro de modificar el item?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          axios.patch(`/api/item/${id}`, {
            description: this.description,
          })
            .then(response => {
              console.log('Respuesta del backend:', response);
              Swal.fire({
                icon: 'success',
                title: 'Item modificado con exito',
                showConfirmButton: false,
              });
              setTimeout(() => {
                window.location.reload();
              }, 2000);
            })
            .catch(error => {
              console.log('Error en la solicitud POST:', error);
              this.error = error.response.data;
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: this.error,
                confirmButtonColor: "#DB504A"
              });
            });
        }
      });
    },

    deleteForm() {
      const itemsMap = new Map([
        [this.langueguesSelectId, 'idiomas'],
        [this.nivelLSelectId, 'niveles'],
        [this.genderSelectId, 'Genero'],
        [this.civilStatusSelectId, 'Estado Civil'],
        [this.typeStudySelectId, 'tipo de Estudio'],
        [this.activitySelectId, 'Actividad'],
        [this.stateStudySelectId, 'Estado'],
        [this.experienceSelectId, 'Nivel de experiencia'],
        [this.AspiresSelectId, 'puesto'],
        [this.salarySelectId, 'salario'],
        [this.transportSelectId, 'transporte'],
        [this.branchOfficeSelectId, 'sucursal'],
        [this.reasonSelectId, 'razon'],
        [this.typeDSelectId, 'tipo de discapacidad'],
        [this.avaivalitySelectId, 'disponibilidad'],
        [this.typeJobSelectId, 'tipo de trabajo'],
        [this.typeRelashionSelectId, 'tipo de relacion'],
        [this.statePSelectId, 'estado de la postulacion']
      ]);
    
      let id = null;
      let itemName = null;
    
      for (const [itemId, itemName] of itemsMap) {
        if (itemId) {
          id = itemId;
          break;
        }
      }
    
      if (!id) {
        return;
      }
    
      Swal.fire({
        title: 'Eliminar',
        text: '¿Estás seguro de eliminar el item?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          axios.patch(`/api/item/delete/${id}`)
            .then(() => {
              Swal.fire({
                icon: 'success',
                title: 'Item actualizado exitosamente',
                showConfirmButton: false,
              });
              setTimeout(() => {
                window.location.reload();
              }, 2000);
            })
            .catch(error => {
              this.error = error.response.data;
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: this.error,
                confirmButtonColor: '#DB504A'
              });
            });
        }
      });
    },

    activeForm() {
      const itemsMap = new Map([
        [this.langueguesSelectId, 'idiomas'],
        [this.nivelLSelectId, 'niveles'],
        [this.genderSelectId, 'Genero'],
        [this.civilStatusSelectId, 'Estado Civil'],
        [this.typeStudySelectId, 'tipo de Estudio'],
        [this.activitySelectId, 'Actividad'],
        [this.stateStudySelectId, 'Estado'],
        [this.experienceSelectId, 'Nivel de experiencia'],
        [this.AspiresSelectId, 'puesto'],
        [this.salarySelectId, 'salario'],
        [this.transportSelectId, 'transporte'],
        [this.branchOfficeSelectId, 'sucursal'],
        [this.reasonSelectId, 'razon'],
        [this.typeDSelectId, 'tipo de discapacidad'],
        [this.avaivalitySelectId, 'disponibilidad'],
        [this.typeJobSelectId, 'tipo de trabajo'],
        [this.typeRelashionSelectId, 'tipo de relacion'],
        [this.statePSelectId, 'estado de la postulacion']
      ]);
    
      let id = null;
      let itemName = null;
    
      for (const [itemId, itemName] of itemsMap) {
        if (itemId) {
          id = itemId;
          break;
        }
      }
    
      if (!id) {
        return;
      }
    
      Swal.fire({
        title: 'Activar',
        text: '¿Estás seguro de activar el item?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          axios.patch(`/api/item/delete/${id}`)
            .then(() => {
              Swal.fire({
                icon: 'success',
                title: 'Item actualizado exitosamente',
                showConfirmButton: false,
              });
              setTimeout(() => {
                window.location.reload();
              }, 2000);
            })
            .catch(error => {
              this.error = error.response.data;
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: this.error,
                confirmButtonColor: '#DB504A'
              });
            });
        }
      });
    },

    activeItem() {
      const selectedCategories = this.inactiveCategories.filter(category => category.selected);
      selectedCategories.forEach(category => {
        const id = category.id;
        axios.patch(`/api/item/active/${id}`, {
          activeItems: true
        })
        Swal.fire({
          title: 'Activar',
          text: '¿Estás seguro de activar el item?',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#198754',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Confirmar',
          cancelButtonText: 'Cancelar',
        }).then((result) => {
          if (result.isConfirmed) {
            Swal.fire({
              icon: 'success',
              title: 'Item activado con exito',
              showConfirmButton: false,
            })
            setTimeout(() => {
              window.location.reload();
            }, 2000);
          }
        })
      });
    },

    addItems() {
      let selectedCategory = this.itemsCategory.find(category => category.name === this.newItems.category);
      if (selectedCategory) {
        let addItem = {
          description: this.newItems.description,
          category: {
            id: selectedCategory.id
          }
        };
        axios.post("/api/items", addItem)
          .then(response => {
            console.log("éxito");
          })
          .catch(error => {
            this.error = error.response.data;
          });
      }
    },

    getItems() {
      axios.get("/api/items")
        .then(response => {
          this.items = response.data;
          this.inactiveCategories = this.items.filter(category => category.activeItems === false)
            .map(category => ({
              ...category,
              selected: false,
              id: category.id
            }));
          console.log(this.items)
          console.log(this.inactiveCategories)
        })

        .catch(error => {
          this.error = error.response.data;
        });
    },

    addAdmin() {
      let addAdministrator = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        branchOffice: this.branchOffice
      }
      Swal.fire({
        title: 'Estas seguro del registro?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Registrar'
      }).then((result) => {
        if (result.isConfirmed) {
          axios.post('/api/admin/administrator', addAdministrator)
            .then((response) => {
              Swal.fire({
                confirmButtonColor: '#198754',
                icon: 'success',
                title: 'Éxito',
                text: 'Cuenta creada con éxito.'
              }).then(() => {
                window.location.reload();
              });
            })
            .catch((error) => {
              Swal.fire({
                confirmButtonColor: '#dc3545',
                icon: 'error',
                title: 'Error',
                text: 'Ha ocurrido un error. Por favor, inténtelo de nuevo.'
              });
              console.error(error);
            });
        }
      })
    },

    addRecruiter() {
      let addRecruiters = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        branchOffice: this.branchOffice
      }
      Swal.fire({
        title: 'Estas seguro del registro?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Registrar'
      }).then((result) => {
        if (result.isConfirmed) {
          axios.post('/api/admin/recruiter', addRecruiters)
            .then((response) => {
              Swal.fire({
                confirmButtonColor: '#198754',
                icon: 'success',
                title: 'Éxito',
                text: 'Cuenta creada con éxito.'
              }).then(() => {
                window.location.reload();
              });
            })
            .catch((error) => {
              Swal.fire({
                confirmButtonColor: '#dc3545',
                icon: 'error',
                title: 'Error',
                text: 'Ha ocurrido un error. Por favor, inténtelo de nuevo.'
              });
              console.error(error);
            });
        }
      })
    },

    updateData(category) {
      if (category === 'admin') {
        this.updateAdmin();
      } else if (category === 'recruiter') {
        this.updateRecruiter();
      }
    },

    updateRecruiter() {
      console.log('Recruiter ID:', this.recruitersSelectId);
      let recruiter = {
        id: this.recruitersSelectId,
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        rol: this.rol,
        branchOffice: this.branchOffice
      }
      Swal.fire({
        title: 'Estas seguro del registro?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Registrar'
      }).then((result) => {
        if (result.isConfirmed) {
          axios.patch(`/api/admin/recruiter/${this.recruitersSelectId}`, recruiter)
            .then((response) => {
              Swal.fire({
                confirmButtonColor: '#198754',
                icon: 'success',
                title: 'Éxito',
                text: 'Cuenta creada con éxito.'
              }).then(() => {
                window.location.reload();
              });
            })
            .catch((error) => {
              Swal.fire({
                confirmButtonColor: '#dc3545',
                icon: 'error',
                title: 'Error',
                text: 'Ha ocurrido un error. Por favor, inténtelo de nuevo.'
              });
              console.error(error);
            });
        }
      })
    },

    updateAdmin() {
      let admin = {
        id: this.adminSelectId,
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        rol: this.rol,
        branchOffice: this.branchOffice
      }
      Swal.fire({
        title: 'Estas seguro de realizar la modificación?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Registrar'
      }).then((result) => {
        if (result.isConfirmed) {
          axios.patch(`/api/admin/administrator/${this.adminSelectId}`, admin)
            .then((response) => {
              Swal.fire({
                confirmButtonColor: '#198754',
                icon: 'success',
                title: 'Éxito',
                text: 'Cuenta modificada con éxito.'
              }).then(() => {
                window.location.reload();
              });
            })
            .catch((error) => {
              Swal.fire({
                confirmButtonColor: '#dc3545',
                icon: 'error',
                title: 'Error',
                text: 'Ha ocurrido un error. Por favor, inténtelo de nuevo.'
              });
              console.error(error);
            });
        }
      })
    },

    deleteData(category) {
      if (category === 'admin') {
        this.activeInactiveAdmin();
      } else if (category === 'recruiter') {
        this.actineInactiveRecruiter();
      }
    },

    activeInactiveRecruiter() {
      console.log(this.recruitersSelectId)
      Swal.fire({
        title: 'Eliminar',
        text: '¿Estás seguro de eliminar al reclutador?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          const activeStatus = this.activeR ? false : true;
    
          axios.patch(`/api/admin/active/recruiter/${this.recruitersSelectId}`, {
            activeRecruiter: activeStatus
          })
          .then(() => {
            Swal.fire({
              icon: 'success',
              title: 'Reclutador actualizado exitosamente',
              showConfirmButton: false,
            });
            setTimeout(() => {
              window.location.reload();
            }, 2000);
          })
          .catch(error => {
            this.error = error.response.data;
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: this.error,
              confirmButtonColor: '#DB504A'
            });
          });
        }
      });
    },

    activeInactiveAdmin() {
      Swal.fire({
        title: 'Eliminar',
        text: "¿Estás seguro de eliminar al administrador?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Confirmar',
        cancelButtonText: 'Cancelar',
      }).then((result) => {
        if (result.isConfirmed) {
          const activeStatus = this.activeA ? false : true;
          axios.patch(`/api/admin/active/administrator/${this.adminSelectId}`, {
            activeAdmin: activeStatus
          })
            .then(() => {
              Swal.fire({
                icon: 'success',
                title: 'Administrador eliminado exitosamente',
                showConfirmButton: false,
              });
              setTimeout(() => {
                window.location.reload();
              }, 2000);
            })
            .catch(error => {
              this.error = error.response.data;
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: this.error,
                confirmButtonColor: "#DB504A"
              });
            });
        }
      });
    },

    getRecruiters() {
      axios.get("/api/users/recruiters")
        .then(response => {
          this.usersRecruiters = response.data;
          console.log(this.usersRecruiters)
          this.recruitersActive = this.usersRecruiters.filter(active => active.activeRecruiter === true)
          this.inactiveRecruiters = this.usersRecruiters.filter(active => active.activeRecruiter === false)
            .map(active => ({
              ...active,
              selected: false,
              id: active.id
            }));
          console.log(this.inactiveRecruiters)
        })

        .catch(error => {
          this.error = error.response.data;
        });
    },

    getAdmins() {
      axios.get("/api/users/admin")
        .then(response => {
          this.usersAdmin = response.data;
          console.log(this.usersAdmin)
          this.adminActive = this.usersAdmin.filter(active => active.activeAdmin === true)
          this.inactiveAdmins = this.usersAdmin.filter(active => active.activeAdmin === false)
            .map(active => ({
              ...active,
              selected: false,
              id: active.id
            }));
          console.log(this.items)
          console.log(this.inactiveAdmins)
        })

        .catch(error => {
          this.error = error.response.data;
        });
    },

    toggleSectionRecruiters(id) {
      console.log('Recruiter ID:', id);
      const selectedRecruiter = this.usersRecruiters.find(recruiter => recruiter.id === this.recruitersSelectId);
      if (selectedRecruiter) {
        this.showSection = true;
        this.firstName = selectedRecruiter.firstName;
        this.lastName = selectedRecruiter.lastName;
        this.email = selectedRecruiter.email;
        this.branchOffice = selectedRecruiter.branchOffice;
        this.rol = selectedRecruiter.rol;
      } else {
        console.log('Reclutador no encontrado');
      }
    },

    toggleSectionAdmins(id) {
      const selectedAdmin = this.usersAdmin.find(admin => admin.id === this.adminSelectId);
      if (selectedAdmin) {
        this.showSection = true;
        this.firstName = selectedAdmin.firstName;
        this.lastName = selectedAdmin.lastName;
        this.email = selectedAdmin.email;
        this.branchOffice = selectedAdmin.branchOffice;
      } else {
        console.log('Administrador no encontrado');
      }
    },

    showNewSection() {
      this.showButtonsSection = false;
    },

    handleStorageEvent(event) {
      if (event.key === "userSession") {
        this.isLoggedIn = event.newValue === "true";
      }
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
    },
  },
}).mount("#app");

