const nombre = document.getElementById('nombre');
const descripcion = document.getElementById('descripcion');
const regBtn = document.getElementById('regBtn');
const taskContainer = document.getElementById('taskContainer');
const doingContainer = document.getElementById('doingContainer');
const doneContainer = document.getElementById('doneContainer');

const registrarse =()=>{
    let taskObj ={
        id:0,
        nombre:nombre.value,
        descripcion:descripcion.value
    };

    console.log(JSON.stringify(taskObj));
    //POST
    let xhr = new XMLHttpRequest();

    //RESPONSE
    xhr.addEventListener('readystatechange' , ()=>{
        if(xhr.readyState == 4){
            console.log(xhr.responseText);
            getAllTask();
        }

    });

    xhr.open('POST','http://localhost:8081/Parcial2ADP/api/ToDoTasks/create');
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(JSON.stringify(taskObj));

    const clean=()=>{
        document.getElementById('nombre').value="";
        document.getElementById('descripcion').value="";
    }
    clean();
}

regBtn.addEventListener('click', registrarse);

const getAllTask =() =>{

    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange' , ()=>{
        if(xhr.readyState ==4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            console.log(response);
            taskContainer.innerHTML = '';
            for(let i =0;i<response.length ; i++){
                let taskDTO = response[i];
                let view = new ToDo(taskDTO);
                view.onDeleteFinish = ()=>{
                    taskContainer.removeChild(document.getElementById('task'+taskDTO.id));
                };
                view.onForwardFinish = () => {
                    getAllDoingTask();
                };
                taskContainer.appendChild(view.render());
            }
        }
    });
    xhr.open('GET','http://localhost:8081/Parcial2ADP/api/ToDoTasks/all');
    xhr.send();
};

getAllTask();


const getAllDoingTask=()=>{
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange' , ()=>{
        if(xhr.readyState ==4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            console.log(response);
             doingContainer.innerHTML = '';
            for(let i =0;i<response.length ; i++){
                let taskDTO = response[i];
                let view2 = new Doing(taskDTO);
                view2.onDeleteFinish = ()=>{
                    doingContainer.removeChild(document.getElementById('doingTask'+taskDTO.id));
                };
                view2.onForwardFinish = () => {
                    getAllDoneTask();
                };
                doingContainer.appendChild(view2.render());
            }
        }
    });
    xhr.open('GET','http://localhost:8081/Parcial2ADP/api/DoingTasks/all');
    xhr.send();

}

getAllDoingTask();

const getAllDoneTask =() =>{

    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange' , ()=>{
        if(xhr.readyState ==4){
            let json = xhr.responseText;
            let response = JSON.parse(json);
            console.log(response);
            taskContainer.innerHTML = '';
            for(let i =0;i<response.length ; i++){
                let taskDTO = response[i];
                let view3 = new Done(taskDTO);
                view3.onDeleteFinish = ()=>{
                    doneContainer.removeChild(document.getElementById('doneTask'+taskDTO.id));
                };
                doneContainer.appendChild(view3.render());
            }
        }
    });
    xhr.open('GET','http://localhost:8081/Parcial2ADP/api/DoneTasks/all');
    xhr.send();
};

getAllDoneTask();