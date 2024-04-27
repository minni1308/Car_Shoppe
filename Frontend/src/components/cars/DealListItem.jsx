import React from 'react';
// import {history} from '../../routers/AppRouter.jsx'
import '../../style/dealListItem.css'
import { AiFillCar } from 'react-icons/ai'
import { FaIdeal } from 'react-icons/fa'
import {
    Card, CardImg, CardText, CardBody,
    CardTitle, CardSubtitle, Button
} from 'reactstrap';
//   style = {{border: "solid" ,display:"flex", flexDirection:"row", gap:"50px", cursor:"pointer"}}

// const DealListItem = ({ id, brand_name, car_name, price, dealer_name, image}) =>{
// const history = useHistory();

//      return (
//     <div style = {{display:"flex", flexDirection:"row", gap:"50px", cursor:"pointer"}} onClick={() => history.push(`/cars/${id}`)}>
//         <img src={require(`../../images/${image}`).default} alt="Deal_Image"/>
//         <div>
//         <h3>{brand_name}</h3>
//         <h3>{car_name}</h3>
//         <p>USD  {price}</p>
//         <p>{dealer_name}</p>
//         </div>
//     </div>
// );
// }
// 
const DealListItem = ({ id, brand_name, car_name, price, dealer_name, carImage, history, mileage, engine_displacement, seating_capacity, type, pushTo }) => {
    return (
        <div style={{ display: "flex", flexDirection: "row", gap: "50px" }}>
            <div className="card1" onClick={() => history.push(`${pushTo}${id}`)} style={{ display: "flex", flexDirection: "row" }}>

                <Card style={{ width: "100%" }}>
                    <div style={{ padding: "8% 8% 0 8%" }}>
                        {/* <CardImg top width="100%" src={require(`../../images/Lamborghini-Aventador.webp`).default} alt="Card image cap" /> */}
                        <CardImg top width="100%" src={"data:image/jpeg;base64," + carImage} alt="Card image cap" />
                        {/* <CardImg top width="100%" src={"Lamborghini-Aventador.webp"} alt="Card image cap" /> */}
                        {/* <img src={"data:image/jpeg;base64," +carImage} alt="Deal_Image"/> */}
                        {/* <div style={{border:"solid white"}}> */}
                    </div>
                    <div style={{ textAlign: "left", paddingLeft: "8%", paddingTop: "5%", paddingBottom: "5%", borderBottom: "1px solid #909090" }}>
                        <CardTitle style={{ color: "#909090" }}>{brand_name}</CardTitle>
                        <CardSubtitle style={{ fontSize: "150%" }}><AiFillCar />  {car_name}</CardSubtitle>
                        <CardSubtitle style={{ margin: "0", fontSize: "120%" }}> {`$ ${price}`} </CardSubtitle>
                    </div>
                    <CardBody style={{ textAlign: "left", padding: "3% 0 3% 8%", backgroundColor: "#f7f7f7" }}>
                        <CardText>Mileage: {mileage} mph</CardText>
                        <CardText>Type: {type}</CardText>
                        <CardText>Engine displacement: {engine_displacement} cc</CardText>
                        <CardText>Seating Capacity: {parseInt(seating_capacity)} seater</CardText>
                        <CardText style={{ padding: "0", margin: "0", color: "#909090" }}><FaIdeal />{dealer_name}</CardText>
                    </CardBody>
                </Card>
            </div>
        </div>
    );
}

export default DealListItem;