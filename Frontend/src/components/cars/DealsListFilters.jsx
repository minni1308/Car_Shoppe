import React from 'react';
import {Slider,Chip,TextField} from '@mui/material';
import {connect} from 'react-redux';
import {setBudget,setBodyType,setSearch,setSort,setOrder} from '../../actions/cars/filters';
import {getCustomerCarBudget} from '../../customisation/cars.js';
import '../../style/dealsListFilters.css'
import { InputGroup, InputGroupAddon, InputGroupText, Input } from 'reactstrap';
// import { MDBCol, MDBInput } from "mdbreact";
import { MDBContainer, MDBFormInline, MDBIcon } from "mdbreact";
import { BorderAllRounded, Search } from '@mui/icons-material';
import { createTheme } from "@mui/material/styles";
import { ThemeProvider } from '@mui/styles';


const muiTheme = createTheme({
//   overrides:{
//     MuiSlider: {
//       thumb:{
//       color: "rgb(255, 51, 51)",
//       },
//       track: {
//         color: 'rgb(255, 51, 51)'
//       },
//       rail: {
//         color: 'rgb(255, 51, 51)'
//       },
//       main: {
//         color: 'rgb(255, 51, 51)'
//       },
//     }
// },
palette: {
  primary: {
    main: 'rgb(255, 51, 51)'
  }
}

});

class DealsFilters extends React.Component{

    constructor(props){
        super(props);

        this.state = {
            value: [10,20],
            bodyTypes:[{label:"SUV",variant:"outlined"},{label:"Hatchback",variant:"outlined"},{label:"Sedan",variant:"outlined"},{label:"MUV",variant:"outlined"},{label:"Minivan",variant:"outlined"},
            {label:"Coupe",variant:"outlined"},{label:"Hybrid",variant:"outlined"},{label:"Luxury",variant:"outlined"},{label:"Convertible",variant:"outlined"},{label:"Pickup Truck",variant:"outlined"},{label:"Wagon",variant:"outlined"}],
            searchInput:[],
            input:"",
            sortBy:"price",
            order:1
          }
        }


        getCoordinate(val){

          let coord;
          coord = parseFloat(val/100000).toFixed(1);
          console.log("coord = ",coord);
          if(coord<=10)
            {
              coord = (parseFloat(coord)*2);
              console.log("1");
              return parseInt(coord);
            }
          else if(coord>10 && coord<=50)
          {
            
              coord=  parseFloat(coord)+10;
              console.log("2",coord);
              return parseInt(coord);
          }
          else if(coord>50 && coord<=100)
          {
            console.log("3");
            coord=60+(parseFloat(coord)-50)/2.5;
              return parseInt(coord);
          }
          else if(coord>100 && coord<=2000)
          {
            console.log("4");
            coord = 80+parseFloat(coord)/100; 
            return parseInt(coord);
          }
          console.log("5");
        }

        componentDidMount(){
          const budget = parseFloat(getCustomerCarBudget());
          const minBudget = budget - (0.4*budget);
          const maxBudget = budget + (0.4*budget);
          //set slider coordinates //Done
          this.setState({value:[this.getCoordinate(minBudget),this.getCoordinate(maxBudget)]});
          this.props.dispatch(setBudget(minBudget,maxBudget));
          this.props.dispatch(setSearch([""]));
          this.props.dispatch(setBodyType([]));
          this.props.dispatch(setOrder(1));
          this.props.dispatch(setSort("price"));          

          console.log("min cord = "+ this.getCoordinate(minBudget)+" max cord = "+this.getCoordinate(maxBudget));
        }

          onSearch(e){
            const input=e.target.value.trim();
            let keywords=[""];
             this.setState({
              input
             });

             if((e.keyCode===13||e.keyCode===32)&& input && this.state.searchInput.indexOf(input)===-1)
         { this.setState((prevState)=>({
            searchInput:prevState.searchInput.concat(input)
          }));
        }

        if((e.keyCode===13||e.keyCode===32))
        this.setState({
          input:""
         });
         keywords = this.state.searchInput;
         if(input)
         keywords = this.state.searchInput.concat(input);
         if(keywords.length===0)
         keywords=[input];
         this.props.dispatch(setSearch(keywords));

        }
         

       onSearchDelete(data){ 
        const searchInput = this.state.searchInput.filter(item=>item!=data);
        let keywords=[""];
        this.setState({searchInput});
        keywords = searchInput;
        if(keywords.length===0)
        keywords=[""];
        this.props.dispatch(setSearch(keywords));
     }

        onBudgetChange(e,data){
          this.setState({value:data});
          console.log(`data[0] val:${data[0]}`);
          console.log(`data[1] val:${data[1]}`);
          let min,max;
          if(data[0]<=20){
            //console.log(`min price: ${(data[0]/2)*100000}`)
            min = (data[0]/2)*100000;
          }
          else if(data[0]>20 && data[0]<=60){
            let y=10;
            let x=data[0];
            let inc=parseFloat((x-20)/5);
            inc=inc*5;
            inc=inc+y;
           // console.log(`min price:${inc*100000}`);
            min = inc*100000;
          }
          else if(data[0]>60 && data[0]<=80){
            let x=data[0];  
            let y=50;
            let inc=parseFloat((x-60)/4);
            inc = inc*10;
            inc = inc+y;
            min = inc*100000;
            //console.log(`min price:${inc*100000}`);
          }
          else if(data[0]>80 && data[0]<=100){
            let x=data[0];
            x = x-79;
            min = x*10000000;
            //console.log(`min price:${x*10000000}`);
          }
          // for data[1]
          if(data[1]<=20){
            //console.log(`max price: ${(data[1]/2)*100000}`)
            max = (data[1]/2)*100000;
          }
          else if(data[1]>20 && data[1]<=60){
            let y=10;
            let x=data[1];
            let inc=parseFloat((x-20)/5);
            inc=inc*5;
            inc=inc+y;
            //console.log(`max price:${inc*100000}`);
            max = inc*100000;
          }
          else if(data[1]>60 && data[1]<=80){
            let x=data[1];  
            let y=50;
            let inc=parseFloat((x-60)/4);
            inc = inc*10;
            inc = inc+y;
            max = inc*100000;
           // console.log(`max price:${inc*100000}`);
          }
          else if(data[1]>80 && data[1]<=100){
            let x=data[1];
            x = x-79;
            max =x*10000000;
            //console.log(`max price:${x*10000000}`);
          }

          this.props.dispatch(setBudget(min,max));
          console.log(`min price:${min} max price:${max}`);
        }

        onTypeChange(data){
          const types = this.state.bodyTypes.map((type)=>{
            if(type===data){
            type.variant = type.variant==="outlined"? "default":"outlined";}
            return type;
          }  );
          this.setState({bodyTypes:types});
          const filteredTypes = types.map((type)=>{
              if(type.variant==="default")
                return type.label;
          });
          this.props.dispatch(setBodyType(filteredTypes.filter((type)=>type!=undefined)));
        }
        
        changeOrder(){
          const order = this.state.order;
          this.setState({order:(order*-1)});
          this.props.dispatch(setOrder(order*-1));
        }
      
        render() {
          const marks = [
            {
              value: 1,
              label: '50K',
            },
            {
              value: 20,
              label: '1M',
            },
            {
              value: 60,
              label: '5M',
            },
            {
              value: 80,
              label: '10M',
            },
            {
              value: 99,
              label: '200M',
            }
          ];

        

          return (
            <div  id="dealsListFilterMainBackground" style={{font: "15px Arial, sans-serif",textShadow:"1px 1px rgba(0,0,0,0.2)",width:270, margin:30}}>

          <div >  <p className="filter_heading">Sort By</p> <select id="sortOption" style={{padding:"3%",color:"#909090"}}
                    className="select"
                    value={this.state.sortBy} 
                    onChange={(e)=>{
                        this.setState({sortBy:e.target.value});
                        switch(e.target.value){
                            case 'name': this.props.dispatch(setSort("name"));break;
                            case 'price': this.props.dispatch(setSort("price"));break;
                        }
                    }}
          
                    >
          
                        <option style={{padding:"5%"}} value="name">Name</option>
                        <option style={{padding:"5%"}} value="price">Price</option>
                    </select>
                    {(this.state.order===1)?<button className="sortButton" onClick={()=>this.changeOrder()}>Ascending</button>:<button className="sortButton" onClick={()=>this.changeOrder()}>Descending</button>}<br/><br/>
                    </div>
                    <p className="filter_heading" >Search Car</p>
                    <ThemeProvider theme={muiTheme}>
            <input type="search" className="search1" style={{padding:"5%"}}
            value={this.state.input}
            label="Search Cars"
            variant="outlined"
            placeholder="Search Cars"
            color="primary"
            onChange={e=>this.onSearch(e)}
            onKeyDown={e=>this.onSearch(e)}
            />
            
            {/* <button style={{backgroundColor:"red",color:"white"}} onClick={e=>this.onSearch(e)}>Search</button> */}
            {this.state.searchInput.map((type,index) => (<Chip 
              label={type} 
              key={index}
              variant = "outlined"
              color="primary"
              onDelete={()=>this.onSearchDelete(type)}
              />))}<br />
              </ThemeProvider>
            <p className="filter_heading" >Select Budget:</p>
            <Slider
              value={this.state.value}
              onChange={(e,data)=>this.onBudgetChange(e,data)}
              //color="rgb(255, 51, 51)"
              marks={marks}
              min={1}
              max={99}
              scale={(x) =>{
                if(x<=20){ //x%2==0 && 
                  return parseFloat(x/2).toFixed(1);
                }
                else if(x>20 && x<=60){ //x%5==0 && 
                  let y=10;
                  let inc = parseFloat((x-20)/5);
                  inc=inc*5;
                  return parseFloat(y+inc).toFixed(1);
                }
                else if(x>60 && x<80){ //x%4==0 && 
                  let y=50;
                  let inc=parseFloat((x-60)/4);
                  inc = inc*10;
                  return parseFloat(inc+y).toFixed(1);
                }
                else if(x>=80 && x<=100){
                  return x-79;
                }
                
              }
              }//change
              valueLabelDisplay="auto"
             />
             <br />
            <ThemeProvider theme={muiTheme}>
            <p className="filter_heading" >Select Body Type:</p>
            {this.state.bodyTypes.map((type,index) => (
              <Chip 
              label={type.label} 
              key={index}
              variant = {type.variant}
              color="primary"
              clickable={true}
              onClick={()=>this.onTypeChange(type)}
              />))}
              </ThemeProvider>
            </div>
          )
        }
      }

const  mapStateToProps = (state) =>{
  return{
    filters:state.filters
  };
}

export default connect(mapStateToProps)(DealsFilters);