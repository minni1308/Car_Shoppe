const defaultDeal = [];
    
    export default (state=defaultDeal,action) => {
        switch(action.type){
            case 'SET_DEALS':
                return action.deals;
                
            default:
                 return state;
        }
    }