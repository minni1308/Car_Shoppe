export const setBudget = (min,max) => {
    console.log("in setBudget -> min "+min+"max "+max);
    return ({
    type:'SET_BUDGET',
    minPrice:min,
    maxPrice:max
})}

export const setBodyType = (types) => ({
    type:'SET_BODY_TYPE',
    types
})

export const setSearch = (keywords) => ({
    type:'SET_SEARCH',
    keywords
})

export const setSort = (sortBy) => ({
    type:'SET_SORT',
    sortBy
})

export const setOrder = (order) => ({
    type:'SET_ORDER',
    order
})
