import { render, cleanup, fireEvent , waitFor} from '@testing-library/react';
import React from 'react';
import axios from 'axios';
import BerlinClockConvertor  from '../BerlinClockConvertor.jsx';


/*
  unit tests to BerlinClockConvertor component using jest.
*/

jest.mock('axios');
const mockedAxios = axios;

// Resets the DOM after each test suite
afterEach(() => {
    cleanup(); 
})

describe('BerlinClockConvertor should mount successfully', () => {
    
    it('should show page title', () => {
        const {container} = render(<BerlinClockConvertor/>);
        expect(container.querySelector('h1').textContent.includes('Digital Time / Berlin Clock Convertor')).toBe(true);
    });

    it('should contain conversion type selection input', () => {
        const {container} = render(<BerlinClockConvertor/>);
        expect(Array.from(container.querySelectorAll('div.main_operation_label')).filter(el => el.textContent.includes('From Digital Time to Berlin Clock')).length).toBe(1);
        expect(Array.from(container.querySelectorAll('div.main_operation_label')).filter(el => el.textContent.includes('From Berlin Clock to Digital Time')).length).toBe(1);
    });

    /*
     unit test : checking error message when digital time is not in correct format, we trigger button after updating input, then we check error message
    */

    it('should show error message when digital time is not in correct format', () => {
        const {container} = render(<BerlinClockConvertor/>);
        const  FromDigTimeToBerClckBtn = container.querySelector('#digToBerCheck');
        fireEvent.click(FromDigTimeToBerClckBtn); 
        const  input = container.querySelector('input.main_form_input');
        fireEvent.change(input, {target: {value: '15:32:63'}}) ;
        expect(container.querySelector('div.main_error').textContent.includes('Please check time format (hh:mm:ss)')).toBe(true);
    });

    /*
     unit test : checking error message when berlin clock is not in correct format, we trigger button after updating input, then we check error message
    */

    it('should show error message when berlin clock is not in correct format', () => {
        const {container} = render(<BerlinClockConvertor/>);
        const  FromBerClckToToBtn = container.querySelector('#berToDigCheck');
        fireEvent.click(FromBerClckToToBtn); 
        const  input = container.querySelector('input.main_form_input');
        fireEvent.change(input, {target: {value: 'ORRROOOOOYYRYYROOOOOY'}}) ;
        expect(container.querySelector('div.main_error').textContent.includes('Please check berline clock format')).toBe(true);
    });

});

describe('should convert digital time to berlin clock successfully', () => {

    mockedAxios.get.mockResolvedValueOnce({
        data: 'ORRROOOOOYYRYYROOOOOYYOO'
    });

    /*
     unit test : after mocking axios to return correct berlin clock string, we trigger convert button and check response
    */

    it('should convert digital time to berlin clock successfullyt',async() => {
        const {container} = render(<BerlinClockConvertor/>);
        const  FromDigTimeToBerClckBtn = container.querySelector('#digToBerCheck');
        fireEvent.click(FromDigTimeToBerClckBtn); 
        const  input = container.querySelector('input.main_form_input');
        fireEvent.change(input, {target: {value: '15:32:21'}})
        const  convertButton = container.querySelector('button.main_form_submit_input');
        fireEvent.click(convertButton);
        await waitFor(() => {
            expect(axios.get).toHaveBeenCalledTimes(1);
            expect(container.querySelector('div.berlin_clock_value').textContent.includes('ORRROOOOOYYRYYROOOOOYYOO')).toBe(true);
        });
    });
});

describe('should convert berlin clock to digital time successfully', () => {

    mockedAxios.get.mockResolvedValueOnce({
        data: '15:32'
    });

    /*
     unit test : after mocking axios to return correct digital time string, we trigger convert button and check response
    */

    it('should convert berlin clock to digital time successfully',async() => {
        const {container} = render(<BerlinClockConvertor/>);
        const  FromBerClckToToBtn = container.querySelector('#berToDigCheck');
        fireEvent.click(FromBerClckToToBtn); 
        const  input = container.querySelector('input.main_form_input');
        fireEvent.change(input, {target: {value: 'ORRROOOOOYYRYYROOOOOYYOO'}})
        const  convertButton = container.querySelector('button.main_form_submit_input');
        fireEvent.click(convertButton);
        await waitFor(() => {
            expect(axios.get).toHaveBeenCalledTimes(2);
            expect(container.querySelector('div.main_result_dig_time_value').textContent.includes('15:32')).toBe(true);
        });
    });
});
