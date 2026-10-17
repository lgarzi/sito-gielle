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

  // Moduli: controllo dei campi obbligatori. L'invio vero va collegato a un servizio (vedi README).
  document.querySelectorAll('form[data-demo]').forEach(function(f){
    f.addEventListener('submit',function(e){
      e.preventDefault();
      var msg=f.querySelector('.form-msg'),bad=[];
      f.querySelectorAll('[required]').forEach(function(el){
        var ok=el.type==='checkbox'?el.checked:el.type==='email'?/^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(el.value):el.type==='file'?el.files.length>0:el.value.trim()!=='';
        el.classList.toggle('invalid',!ok);if(!ok)bad.push(el);
      });
      var file=f.querySelector('input[name="curriculum"]');
      if(file&&file.files[0]&&file.files[0].size>5*1024*1024){file.classList.add('invalid');bad.push(file);}
      if(bad.length){msg.className='form-msg err';msg.textContent='Controlla i campi evidenziati.';bad[0].focus();return;}
      msg.className='form-msg ok';msg.textContent='Grazie! Il modulo è compilato correttamente (invio non ancora attivo in questa versione).';
      f.reset();
    });
  });

})();
