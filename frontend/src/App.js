import React, {useEffect, useState} from 'react';
import axios from 'axios';
import { useFormik } from 'formik';
import {Container, CssBaseline} from '@material-ui/core'
import HeaderComponent from './components/header/header.component';
import TodoListComponent from './components/todo-list/todo-list.component';
import ModalComponent from './components/modal/modal.component';
import {todoFilters} from './utils/constants'

function getCurrentDate() {
  const now = new Date();
  return now.toISOString().slice(0, 10);
}

function App() {

  const [todos, setTodos] = useState([])

  const [open, setOpen] = useState(false)

  const [editMode, setEditMode] = useState(false)

  const [editTodo, setEditTodo] = useState({})

  const [filter, setFilter] = useState(todoFilters.SHOW_ALL)

  const [filteredTodos, setFilteredTodos] = useState([])

  const [refresh, setRefresh] = useState(false)

  const formik = useFormik({
    initialValues: {
        title: '',
        priority: 'LOW',
        dueDate: getCurrentDate()
    }
  })

  useEffect(() => {
    if(editMode) {
      formik.values.title = editTodo.title;
      formik.values.priority = editTodo.priority;
      formik.values.dueDate = editTodo.dueDate;
    } 
    else {
      formik.values.title = '';
      formik.values.priority = 'LOW';
      formik.values.dueDate = getCurrentDate();
    }
  }, [editMode]);

  useEffect(() => {
    handleStatus(filter)
  }, [filter]);

  useEffect(() => {
    axios
       .get("http://localhost:8080/todo")
       .then((res) => {
         setTodos(res.data)
        })
       .catch((err) => console.log(err));
  }, [])

  useEffect(() => {
    if(refresh) {
      axios
       .get("http://localhost:8080/todo")
       .then((res) => {
         setTodos(res.data)
         setRefresh(false)
        })
       .catch((err) => console.log(err));
    }
  }, [refresh]);

  const handleDialogOpen = () => {
    setOpen(true)
  }

  const handleDialogClose = () => {
    setOpen(false)
  }

  const handleSubmit = () => {
      
      const {title, priority, dueDate} = formik.values;
      
      if(!editMode) {
        const newTodo = { title: title, priority: priority, dueDate: dueDate, completed:false}
        axios.post("http://localhost:8080/todo", newTodo)
             .then((res) => setRefresh(true))
      }
      else {
        const newTodos = [...todos]
        const todo = todos.find(todo => todo.id === editTodo.id)
        todo.title = title;
        todo.priority = priority;
        todo.dueDate = dueDate;
        if(todo.id) {
          axios.put(`http://localhost:8080/todo/${todo.id}`, todo)
               .then(() => setRefresh(true))
        }
        setEditMode(false)
        setEditTodo({})
        setTodos(newTodos)
      }

      setOpen(false);
      formik.values.title = '';
      formik.values.priority = 'LOW';
      formik.values.dueDate = getCurrentDate();
  }

  const handleDelete = (id) => {
    if(id) {
      axios.delete(`http://localhost:8080/todo/${id}`).then(() => setRefresh(true))
    }
  }

  const handleEdit = (todo) => {
    setOpen(true);
    setEditMode(true);
    setEditTodo(todo);
  }

  const handleCompleted = (todo) => {
    const indexTodo = todos.findIndex(t => t.id === todo.id);
    axios.put(`http://localhost:8080/todo/${todo.id}`, {...todos[indexTodo], completed: !todos[indexTodo].completed})
         .then(() => setRefresh(true))
  }

  const handleStatus = (selectedFilter) => {
    if(selectedFilter === todoFilters.SHOW_COMPLETED) {
      setFilter(todoFilters.SHOW_COMPLETED);
      const newTodos = todos.filter(todo => todo.completed === true);
      setFilteredTodos(newTodos);
    }
    else if(selectedFilter === todoFilters.SHOW_ACTIVE) {
      setFilter(todoFilters.SHOW_ACTIVE)
      const newTodos = todos.filter(todo => todo.completed === false);
      setFilteredTodos(newTodos);
    }
    else {
      setFilter(todoFilters.SHOW_ALL)
      setFilteredTodos(todos);
    }
  }

  return (
    <>
      <CssBaseline />
      <Container style = {{backgroundColor: '#cfe8fc'}} maxWidth="sm">
      <HeaderComponent 
                handleOpen={handleDialogOpen}
                handleStatus={handleStatus}
                />
      <TodoListComponent 
                todos={filter === todoFilters.SHOW_ALL ? todos : filteredTodos}
                handleDelete={handleDelete}
                handleEdit={handleEdit}
                handleCompleted={handleCompleted}
                />
      </Container>
      <ModalComponent 
                open={open} 
                handleClose={handleDialogClose} 
                handleSubmit={handleSubmit}
                formik={formik}
                editMode={editMode}
                />
    </>
  );
}

export default App;
