import React, {useState} from 'react';
import { AppBar, Grid, Typography, Button, Tabs, Tab} from '@material-ui/core';
import { makeStyles } from "@material-ui/core/styles"
import AssignmentIcon from '@material-ui/icons/Assignment';
import AssignmentLateIcon from '@material-ui/icons/AssignmentLate';
import AssignmentTurnedInIcon from '@material-ui/icons/AssignmentTurnedIn';
import {todoFilters} from '../../utils/constants'

const useStyles = makeStyles(theme => ({
    title: {
      margin: theme.spacing(4, 0, 2),
      fontWeight: 600
    }, 
    root: {
      flexGrow: 1,
      backgroundColor: theme.palette.background,
      fontWeight: 600
    },
  }))

export default function HeaderComponent({handleOpen, handleStatus}) {

    const classes = useStyles()

    const [filter, setFilter] = useState(todoFilters.SHOW_ALL)
    
    return (
        <>
          <Grid container spacing={3} direction="row" alignItems="center" justifyContent="center">
            <Grid align="left" item xs={9}>
              <Typography variant="h5" className={classes.title}>
                My TODO List
              </Typography>
            </Grid>
    
            <Grid align="right" item xs={3}>
              <Typography variant="h6" className={classes.title}>
                <Button variant="contained" color="primary" onClick={handleOpen}>
                  Add item
                </Button>
              </Typography>
            </Grid>
          </Grid>
          <Grid container spacing={2} direction="row" alignItems="flex-start">
            <Grid item className={classes.root}>
              <AppBar position="static">
                <Tabs
                  onChange={() => handleStatus(filter)}
                  variant="fullWidth"
                >
                  <Tab onClick={() => setFilter(todoFilters.SHOW_ALL)} label="All" icon={<AssignmentIcon/>}/>
                  <Tab onClick={() => setFilter(todoFilters.SHOW_ACTIVE)} label="Active" icon={<AssignmentLateIcon />} />
                  <Tab onClick={() => setFilter(todoFilters.SHOW_COMPLETED)} label="Completed" icon={<AssignmentTurnedInIcon />}/>
                </Tabs>
              </AppBar>
            </Grid>
          </Grid>
        </>
      )

}
