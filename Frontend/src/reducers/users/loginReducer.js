import * as types from '../../actions/users';
const defaultState = {
  clientId:'',
  password:''
  };


export default function(state=defaultState, action) {

  switch(action.type) {
    case types.LOGIN_USER_SUCCESS:
      return { ...state,clientId:action.clientId,password:action.password };
    case types.LOGIN_USER_ERROR:
      return { ...state,clientId:'',password:''};
    default:
      return state;
  }

}

