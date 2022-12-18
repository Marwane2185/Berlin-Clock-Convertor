import { useState } from 'react';
import BerlinClock  from './Components/BerlinClock/BerlinClock.jsx';
import { validTime, validBerlinTime } from './Regex.jsx';
import { getBerlinClockFromDigitalTime, getDigitalTimeFromBerlinClock } from './Services/ConvertorService.js'
import './BerlinClockConvertor.scss';

/*
  This component shows Berlin Clock Convertor, user can selelct conversion type : from digital code to berlin clock or inversly.
  in case berlin clock or digital time string is not in correct format, an error message will be shown.
  if an exception is received from server while making conversion, error message received will be shown to user.
*/

function BerlinClockConvertor() {
  const [conversionType, setConversionType] = useState(true);
  const [changed, setChanged] = useState(false);
  const [serverErrMsg, setServerErrMsg] = useState('');
  const [time, setTime] = useState('');
  const [berlinClk, setBerlinClk] = useState('');
  const [berlinClkStr, setBerlinClkStr] = useState('');
  const [digitalTimeStr, setDigitalTimeStr] = useState('');

  const digitalTimeChecked = () => {
    setConversionType(true);
    clearParams();
  }

  const berlinClockChecked = () => {
    setConversionType(false);
    clearParams();
  }

  const clearParams = () => {
    setChanged(false);
    setTime('');
    setBerlinClk('');
    setDigitalTimeStr('');
    setBerlinClkStr('');
    setServerErrMsg('');
  }

  const updateInputValue = (e) => {
    setServerErrMsg('');
    if(e.target.value !== time) {
      setChanged(true);
    }
    if (conversionType) {
      setTime(e.target.value);
    }else {
      setBerlinClk(e.target.value);
    }
  
  }

  const convert = () => {
    if( conversionType && validTime.test(time)) {
      getBerlinClockFromDigitalTime(time).then(res => {
        setChanged(false);
        setBerlinClkStr(res.data);
        setDigitalTimeStr(time);
      }).catch(error => {
        setServerErrMsg(error.response.data.message);
      });
    }
    if ( !conversionType && validBerlinTime.test(berlinClk)) {
      getDigitalTimeFromBerlinClock(berlinClk).then(res => {
        setChanged(false);
        setDigitalTimeStr(res.data);
        setBerlinClkStr(berlinClk);
      }).catch(error => {
        setServerErrMsg(error.response.data.message);
      });;
    }
  }

  return (
    <div className="main">
      <h1> Digital Time / Berlin Clock Convertor </h1>
      <div className="main_operation">
        <div className="main_operation_label">
          <input type="checkbox"
          id="digToBerCheck"
          className='main_operation_check'
           checked={conversionType}
           onChange={digitalTimeChecked}/>
           From Digital Time to Berlin Clock
        </div>
        <div className="main_operation_label">
          <input type="checkbox"
          id="berToDigCheck"
          className='main_operation_check'
           checked={!conversionType}
           onChange={berlinClockChecked}/>
           From Berlin Clock to Digital Time
        </div>
      </div>
      <div className='main_form'>
        <div className="main_form_label">
          { conversionType ? 'Digital time' : 'Berline clock' }
        </div>
        <div className="main_form_input"> 
          <input className="main_form_input" 
                  type="text" 
                  name="name"
                  value={ conversionType ? time : berlinClk }
                  onChange={updateInputValue}/>
        </div>
        <div className="main_form_submit"> 
        <button className="main_form_submit_input" 
             onClick={convert} 
             style={{ backgroundColor : ((conversionType && validTime.test(time)) 
                      || (!conversionType && validBerlinTime.test(berlinClk))) ? 
                      '#4CAF50' : '#8f92a1'}}>
                        Convert
        </button>
        </div>
      </div>
      <div className="main_error">
        { serverErrMsg.length > 0 ? <div className='main_error_msg'> {serverErrMsg} </div> : '' }
        { changed && time.length > 0 && !validTime.test(time) ?  <div className='main_error_msg'> Please check time format (hh:mm:ss) </div> : '' }
        { changed && berlinClk.length > 0 && !validBerlinTime.test(berlinClk) ?  <div className='main_error_msg'> Please check berline clock format</div> : '' }
      </div>
      <div className='main_result'>
        <div className='main_result_dig_time'>
          <div className='main_result_dig_time_title'> Digital Time </div>
          <div className='main_result_dig_time_value'>
            {digitalTimeStr}
          </div>
        </div>
        <div className='main_result_berlin_clock'>
          <div className='main_result_berlin_clock_title'> Berlin Clock </div>
          <BerlinClock time={ changed ? '' : conversionType ? berlinClkStr : berlinClk}></BerlinClock>
        </div>
      </div>
    </div>
  );
}

export default BerlinClockConvertor;
