// let mainDirection=document.getElementById("mainDirection");
let mainDirections=document.getElementsByClassName("mainDeclaration");
for(var i=0;i<mainDirections.length;i++){
    mainDirections[i].dataset.i=i;
    mainDirections[i].addEventListener('change',function (e){
        // let dopDirection=document.getElementById('dopDirection');
        let dopDirection=document.getElementsByClassName('dopDeclaration')[e.target.dataset.i];
        dopDirection.innerHTML='';
        let startOp=document.createElement('option');
        startOp.setAttribute('disabled','');
        startOp.setAttribute('selected','');
        startOp.value='-1';
        startOp.text='Не выбрано';
        dopDirection.append(startOp);
        $.ajax({
            type: "POST",
            url: "api/getDirection",
            data: {
                'firstDirection': e.target.value,
            },
            success: function (data) {
                console.log(data);
                for(let i in data){
                    let option=document.createElement('option');
                    option.value=i;
                    option.text=data[i];
                    dopDirection.append(option);
                }
            },
            error: function (e) {

                $("#result").text(e.responseText);
                console.log("ERROR : ", e);
                $("#btnSubmit").prop("disabled", false);

            }
        });
    });
}

function getSelectedText(obj) {
    for (i = 0; i < obj.length; i++) {
        if (obj[i].selected == true) {
            return obj[i].innerText; // Ключ должен получить текст опции через свойство innerText объекта опции
        }
    }
}

let updateDopDeclaration=document.getElementById("updateDopDeclaration");
if(updateDopDeclaration){
    updateDopDeclaration.addEventListener("change",function (e){
        let newDopDeclaration=document.getElementById("newDopDeclaration");
        newDopDeclaration.value=getSelectedText(e.target);
    });
}
let updateMainDeclaration=document.getElementById("updateMainDeclaration");
if(updateMainDeclaration){
    updateMainDeclaration.addEventListener("change",function (e){
        let newMainDeclaration=document.getElementById("newMainDeclaration");
        newMainDeclaration.value=getSelectedText(e.target);
    });
}
