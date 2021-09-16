import React from 'react';
import { TextField, Grid, InputLabel, MenuItem, Select} from '@material-ui/core';

export default function ModalContentComponent({formik}) {

    return (
        <Grid container direction="column" spacing={2}>
                <Grid item>
                    <TextField
                        margin="dense"
                        id="title"
                        name="title"
                        label="Title"
                        fullWidth
                        value={formik.values.title}
                        onChange={formik.handleChange}
                        type="text"
                    />
                </Grid>
                <Grid item>
                    <InputLabel id="demo-simple-select-label">Priority</InputLabel>
                    <Select
                        labelId="demo-simple-select-label"
                        id="priority"
                        name="priority"
                        fullWidth
                        value={formik.values.priority}
                        onChange={formik.handleChange}
                    >
                        <MenuItem value="LOW">LOW</MenuItem>
                        <MenuItem value="MEDIUM">MEDIUM</MenuItem>
                        <MenuItem value="HIGH">HIGH</MenuItem>
                    </Select>
                </Grid>
                <Grid item>
                    <TextField
                        id="dueDate"
                        name="dueDate"
                        onChange={formik.handleChange}
                        value={formik.values.dueDate}
                        label="Due date"
                        type="date"
                        style={{width: '100%'}}
                        InputLabelProps={{
                            shrink: true,
                        }}
                    />
                </Grid>
                </Grid>
    )
}