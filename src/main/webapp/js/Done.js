class Done{

    constructor(task) {
        this.task = task;
        this.onDeleteFinish=null;
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
        xhr.open('DELETE','http://localhost:8081/Parcial2ADP/api/DoneTasks/delete/'+this.task.id);
        xhr.send();
    }

    render=()=>{
        let component = document.createElement('div');
        component.id='doneTask'+this.task.id;
        component.className = 'doneComponent';
        let nombre = document.createElement('p');
        let descripcion = document.createElement('p');
        let fecha = document.createElement('small');
        let deleteBtn = document.createElement('button');

        deleteBtn.className = 'delBtn';
        deleteBtn.innerHTML = ' ';

        nombre.innerHTML = this.task.nombre;
        descripcion.innerHTML = this.task.descripcion;
        fecha.innerHTML = this.task.fecha;

        component.appendChild(nombre);
        component.appendChild(descripcion);
        component.appendChild(fecha);
        component.appendChild(deleteBtn);

        deleteBtn.addEventListener('click',this.deleteTask);

        return component;
    }

}