import React from 'react';
import { Button} from '@material-ui/core';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import ModalContentComponent from './modal-content.component';

export default function ModalComponent({ handleClose, open, handleSubmit, formik, editMode }) {

    return (

        <Dialog maxWidth='sm'
                fullWidth
                onClose={handleClose} 
                aria-labelledby="form-dialog-title" 
                open={open}>
            <DialogTitle id="form-dialog-title" onClose={handleClose}>
                {editMode ? 'Update' : 'Add'} the todo details
            </DialogTitle>
            <DialogContent>
                <ModalContentComponent formik={formik} />
            </DialogContent>
            <DialogActions>
                <Button autoFocus onClick={handleSubmit} variant="contained" color="primary">
                    {editMode ? 'Update' : 'Save'}
                </Button>
                <Button autoFocus onClick={handleClose} variant="contained" color="secondary">
                    Cancel
                </Button>
            </DialogActions>
        </Dialog>
    )
}