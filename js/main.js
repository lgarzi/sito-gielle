(function(){
  var hdr=document.getElementById('hdr'),hero=document.querySelector('.hero'),burger=document.getElementById('burger');
  function onScroll(){
    var lim=Math.max(40,hero.offsetHeight-hdr.offsetHeight-40);
    hdr.classList.toggle('solid',window.scrollY>lim&&!hdr.classList.contains('open'));
  }
  window.addEventListener('scroll',onScroll,{passive:true});onScroll();
  burger.addEventListener('click',function(){
    var o=hdr.classList.toggle('open');burger.setAttribute('aria-expanded',o);burger.setAttribute('aria-label',o?'Chiudi il menu':'Apri il menu');onScroll();
  });
  document.querySelectorAll('.mnav a').forEach(function(a){a.addEventListener('click',function(){hdr.classList.remove('open');burger.setAttribute('aria-expanded','false');onScroll();});});

  // Comparsa allo scroll: solo gli elementi fuori schermo partono nascosti
  var reduce=window.matchMedia('(prefers-reduced-motion: reduce)').matches;
  if('IntersectionObserver' in window&&!reduce){
    var io=new IntersectionObserver(function(es){es.forEach(function(e){if(e.isIntersecting){e.target.classList.remove('pending');io.unobserve(e.target);}});},{rootMargin:'0px 0px -8% 0px'});
    document.querySelectorAll('.rv').forEach(function(el){
      if(el.getBoundingClientRect().top>window.innerHeight*.92){el.classList.add('pending');io.observe(el);}
    });
  }

})();
