class ToDo{

    constructor (task){
        this.task = task;
        this.onDeleteFinish=null;
        this.onForwardFinish = null;
    }

    deleteTask = ()=>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState == 4){
                var response = JSON.parse(xhr.responseText);
                if(response.message == 'Success'){
                    if(this.onDeleteFinish != null){
                        this.onDeleteFinish();
                    }
                }else{
                    alert('Could not delete the task');
                }
            }
        });
        xhr.open('DELETE','http://localhost:8081/Parcial2ADP/api/ToDoTasks/delete/'+this.task.id);
        xhr.send();
    }

    nextState = () =>{
        let obj = {
            id:0,
            nombre:this.task.nombre,
            descripcion:this.task.descripcion
        };
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState == 4){
                console.log(xhr.responseText);
                this.deleteTask();
                if(this.onForwardFinish!=null){
                    this.onForwardFinish();
                }
            }
        });
        xhr.open('POST', 'http://localhost:8081/Parcial2ADP/api/DoingTasks/create');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify(obj));
    }


    render =()=>{
        let component = document.createElement('div');
        component.id='task'+this.task.id;
        component.className = 'taskComponent';
        let nombre = document.createElement('p');
        let descripcion = document.createElement('p');
        let fecha = document.createElement('p');
        let deleteBtn = document.createElement('button');
        let nextBt = document.createElement('button');

        deleteBtn.innerHTML = ' ';
        deleteBtn.className = 'delBtn';
        nextBt.className = 'nextBtn';
        nextBt.innerHTML = ' ';

        nombre.innerHTML = this.task.nombre;
        descripcion.innerHTML = this.task.descripcion;
        fecha.innerHTML = this.task.fecha;

        component.appendChild(nombre);
        component.appendChild(descripcion);
        component.appendChild(fecha);
        component.appendChild(deleteBtn);
        component.appendChild(nextBt);

        deleteBtn.addEventListener('click',this.deleteTask);
        nextBt.addEventListener('click', this.nextState);


        return component;
    }
}