/**
 * Navigation — Hide on scroll (mobile)
 * Improves mobile UX by hiding navbar when scrolling down
 */
document.addEventListener('DOMContentLoaded', function() {
  let lastScrollTop = 0;
  const navbar = document.querySelector('.main-navigation');

  if (!navbar) return;

  window.addEventListener('scroll', function() {
    if (window.innerWidth <= 900) {
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
      
      if (scrollTop > lastScrollTop && scrollTop > 100) {
        navbar.classList.add('nav-hidden');
      } else {
        navbar.classList.remove('nav-hidden');
      }
      
      lastScrollTop = Math.max(0, scrollTop);
    } else {
      navbar.classList.remove('nav-hidden');
    }
  }, { passive: true });
});
