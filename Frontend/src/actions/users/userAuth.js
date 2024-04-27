import * as types from './index';

// export const registerUserAction = (user) => {
//   return {
//     type: types.REGISTER_USER,
//     user
//   }
// };

export const loginUserAction = (clientId1,password1) => {
  return {
    type: types.LOGIN_USER,
    clientId:clientId1,
    password:password1
  }
};
