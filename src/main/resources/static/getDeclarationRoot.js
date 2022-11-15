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