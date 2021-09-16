import React from 'react';
import {IconButton, ButtonGroup, Grid, Paper, Typography} from '@material-ui/core';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import DoneIcon from '@material-ui/icons/Done';

function getTextDecoration(todoStatus) {
    return (todoStatus ? 'line-through' : 'none')
}

export default function TodoListComponent({todos, handleDelete, handleEdit, handleCompleted}) {
    return (
        <Grid container direction="column" spacing={3}>
        {
            todos.map((todo) => {
                return (
                    <Grid item key={todo.id}>
                    <Paper elevation={3}>
                        <Grid container style={{padding: "1rem"}} direction="column">
                            <Grid item>
                                <Typography variant="h5" style={{textDecoration: getTextDecoration(todo.completed)}}>
                                    {todo.title}
                                </Typography>
                            </Grid>
                            <Grid item>
                                 <Typography variant="subtitle1">
                                   Priority: {todo.priority}
                                </Typography>
                            </Grid>
                            <Grid item>
                                <Typography variant="subtitle1">
                                    Due date: {todo.dueDate}
                                </Typography>
                            </Grid>
                        </Grid>
                        <Grid container spacing={2} direction="row">
                                <Grid item>
                                <ButtonGroup variant="text" style={{paddingLeft: '5px'}} size="small" aria-label="text primary button group">
                                <IconButton color="primary" aria-label="delete" onClick={() => handleDelete(todo.id)}>
                                    <DeleteIcon />
                                </IconButton>
                                <IconButton color="secondary" aria-label="edit" onClick={() => handleEdit(todo)}>
                                    <EditIcon />
                                </IconButton>
                                <IconButton color="default" aria-label="complete" onClick={() => handleCompleted(todo)}>
                                    <DoneIcon />
                                </IconButton>
                                </ButtonGroup>
                                </Grid>
                            </Grid>
                    </Paper>   
                    </Grid>
                )
            })
        }
        </Grid>
    )

}