var switcher=document.getElementById('chooseRole');
var span1=switcher.getElementsByTagName('span')[0];
var span2=switcher.getElementsByTagName('span')[1];
var formContainer=document.getElementsByClassName('formContainer');
span1.addEventListener('click',function(e){
    span2.className='';
    span1.className='active';
    var h=document.getElementById('hPersonalPage');
    h.innerHTML='Личный кабинет работодателя';
    formContainer[0].style.display='block';
    formContainer[1].style.display='none';
});
span2.addEventListener('click',function(e){
    span1.className='';
    span2.className='active';
    var h=document.getElementById('hPersonalPage');
    h.innerHTML='Личный кабинет учённого';
    formContainer[1].style.display='block';
    formContainer[0].style.display='none';
});