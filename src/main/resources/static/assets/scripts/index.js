
/* PARALLAX */
gsap.registerPlugin(ScrollTrigger);
const sections = gsap.utils.toArray(".panel"),
  container = document.querySelector(".container");
gsap.to(sections, {
  xPercent: -100 * (sections.length - 1),
  ease: "none",
  scrollTrigger: {
    trigger: ".container",
    pin: true,
    scrub: 1,
    snap: 1 / (sections.length - 1),
    end: () => "+=" + container.offsetWidth,
  },
});

/* NABVAR */
const select = selector => document.querySelector(selector);
const navBtn = select('.nav-btn');
navBtn.addEventListener('click', () => {
	navBtn.classList.toggle('nav-open');
	select('.desktop-nav').classList.toggle('mobile-nav-open');
	select('.main-container').classList.toggle('main-container-transform');
});