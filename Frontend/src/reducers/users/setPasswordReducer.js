const defaultUser = {
    clientId:'',
    emailId:'',
    password:''
};

export default (state=defaultUser,action) => {
    switch(action.type){
        case 'SET_PASSWORD':
            return{
                ...state,
                clientId:action.clientId,
                emailId:action.emailId,
                password:action.password
            }
        default:
             return state;
    }
}