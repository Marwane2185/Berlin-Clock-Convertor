import './BerlinClock.scss';

/*
  This component shows Berlin Clock as string and graphic at the same time
*/

function BerlinClock(props) {
    let hours = [...Array(4).keys()];
    let minutes = [...Array(11).keys()];
  return (
    <div className="berlin_clock">
        <div className="berlin_clock_value"> {props.time} </div>
        <div className="berlin_clock_seconds" 
            style={{ backgroundColor : props.time && props.time.charAt(0) === 'Y' ? '#db290d': '#8f92a1'}}/>
        <div className="berlin_clock_hours">
            {hours.map(el => <div  key={el} className="berlin_clock_hours_element"
                                  style={{ backgroundColor : props.time && props.time.charAt(el+1) === 'R' ? '#db290d': '#8f92a1'}}/>)}
        </div>
        <div className="berlin_clock_hours">
             {hours.map(el => <div  key={el} className="berlin_clock_hours_element"
                                   style={{ backgroundColor : props.time && props.time.charAt(el+5) === 'R' ? '#db290d': '#8f92a1'}}/>)}
        </div>
        <div className="berlin_clock_minutes_first_row">
            {minutes.map(el => <div  key={el} className="berlin_clock_minutes_first_row_element"
                                    style={{ backgroundColor : props.time && props.time.charAt(el+9) === 'R' ? '#db290d':  props.time && props.time.charAt(el+9) === 'Y' ? '#fcdf03' : '#8f92a1'}}/>)}
        </div>
        <div className="berlin_clock_minutes_second_row">
            {hours.map(el => <div  key={el} className="berlin_clock_minutes_second_row_element"
                              style={{ backgroundColor : props.time && props.time.charAt(el+20) === 'Y' ? '#fcdf03': '#8f92a1'}}/>)}
        </div>
    </div>
  );
}

export default BerlinClock;
