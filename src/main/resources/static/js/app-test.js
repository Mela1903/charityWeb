document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          // validation
          if (this.validate()) {
            this.currentStep++;
            this.updateForm();
          }
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */

    validate() {


      // data-step=1
      if (this.currentStep === 1) {
        let checkboxElements = form.querySelectorAll("input[type='checkbox']:checked");
        let checkboxCounter = 0;
        for (let i = 0; i < checkboxElements.length; i++) {
          if (checkboxElements[i].checked) {
            checkboxCounter++
          }
        }
        if (checkboxCounter > 0) {
          return true;
        } else {
          alert("wybierz conajmniej jedną opcję");
          return false;
        }

        // data-step=2
      } else if (this.currentStep === 2){
        let bagQuantityElement = form.querySelector("input[type='number']");
        if (bagQuantityElement.value <= 0) {
          alert("minimum 1 worek");
          return false;
        } else {
          return true;
        }

        // data-step=3
      } else if (this.currentStep === 3) {
        let radioElement = form.querySelector("input[type='radio']:checked");
        let radioSelected = 0;
        if (radioElement !== null) {
          return true;
        } else {
          alert("wybierz 1 organizację");
          return false;
        }

        // data-step=4
      } else if (this.currentStep === 4) {

        let errorCounter = 0;
        let address = document.querySelectorAll("div[data-step='4'] div[class='form-section form-section--columns'] input[type='text']");

        let street = address[0].value;
        let errorStreet = document.getElementById("errorStreet");
        if (street === "") {
          errorStreet.innerHTML = "Wymagana nazwa ulicy";
          errorCounter++;
        } else {
          errorStreet.innerHTML = "";
        }

        let city = address[1].value;
        let errorCity = document.getElementById("errorCity");
        if (city === "") {
          errorCity.innerHTML = "Wymagane miasto";
          errorCounter++;
        }else {
          errorCity.innerHTML = "";
        }

        let zipcode = address[2].value;
        let errorZipcode = document.getElementById("errorZipcode");
        if (zipcode === "") {
          errorZipcode.innerHTML = "Wymagany kod";
          errorCounter++;
        } else {
          errorZipcode.innerHTML = "";
        }
        return errorCounter <= 0;
      }
    }

    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation

        // const steps = Array.from(document.querySelectorAll('form div[data-step]'));
        // let index = 0;
        // const active = document.querySelector('form div[data-step].active');
        // index = steps.indexOf(active);
        // steps[index].classList.remove('active');
        //
        // console.log(steps);

        // form.submit() {
        //   return false
        // };
        //
        // this.currentStep = 1;

        // let address = document.querySelectorAll("div[data-step='4'] div[class='form-section form-section--columns'] input[type='text']");
        // let street = address[0].value;
        // let city = address[1].value;
        // let zipcode = address[2].value;
        //
        // let email = document.forms["myform"]["phone"];
        // if (email.value == "") {
        //   alert("Please enter your phone.");
        //   return false;
        // }
        //
        // let text = "";
        //
        // if (street.length === 0) {
        //   text = "Błąd";
        //   form.querySelector("div[data-step='4']").innerHTML = text;
        //   return valid;
        // }



      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
      //
      let address = document.querySelectorAll("div[data-step='4'] div[class='form-section form-section--columns'] input[type='text']");
      let street = address[0].value;
      let city = address[1].value;
      let zipcode = address[2].value;

      let phone = document.querySelector("input[type='tel']").value;
      let pickUpDate = document.querySelector("input[type='date']").value;
      let pickUpTime = document.querySelector("input[type='time']").value;
      let pickUpComments = document.querySelector("textarea").value;

      const contactDetails = document.querySelectorAll("div[data-step='5'] .summary div[class='form-section form-section--columns'] ul li");
      contactDetails[0].innerText = street;
      contactDetails[1].innerText = city;
      contactDetails[2].innerText = zipcode;
      contactDetails[3].innerText = phone;
      contactDetails[4].innerText = pickUpDate;
      contactDetails[5].innerText = pickUpTime;
      contactDetails[6].innerText = pickUpComments;

      let allCheckboxes = form.querySelectorAll("input[type='checkbox']:checked");
      let checkboxText = "";
      for (let i = 0; i < allCheckboxes.length; i++) {
        checkboxText += allCheckBoxes[i].parentNode.lastElementChild.textContent.split(", ").values();
      }
      console.log(allCheckboxes);

      let radioButton = form.querySelector("input[type='radio']:checked");

      }
    }

    // origin
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }

  function setErrorMessage(el, message) {
    $(el).data('errorMessage', message);                 // Przechowywanie komunikatu o błędzie wraz z elementem.
  }

  function getErrorMessage(el) {
    return $(el).data('errorMessage') || el.title;       // Pobranie komunikatu błędu lub tytułu elementu.
  }

  function showErrorMessage(el) {
    var $el = $(el);                                     // Wyszukanie elementu z błędem.
    var errorContainer = $el.siblings('.error.message'); // Czy zawiera już jakiekolwiek błędy?

    if (!errorContainer.length) {                         // Jeżeli nie znaleziono komunikatów o błędach.
      // Utworzenie elementu <span> do przechowywania błędu i dodanie go po elemencie, w którym wystąpił błąd.
      errorContainer = $('<span class="error message"></span>').insertAfter($el);
    }
    errorContainer.text(getErrorMessage(el));             // Dodanie komunikatu o błędzie.
  }

  function removeErrorMessage(el) {
    var errorContainer = $(el).siblings('.error.message'); // Pobranie elementów równorzędnych tej kontrolki formularza używanej do przechowywania komunikatu błędu.
    errorContainer.remove();                               // Usunięcie elementu zawierającego komunikat błędu.
  }

});