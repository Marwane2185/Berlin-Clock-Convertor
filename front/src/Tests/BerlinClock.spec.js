import { render } from '@testing-library/react';
import React from 'react';
import BerlinClock  from '../Components/BerlinClock/BerlinClock.jsx';

/*
  unit tests to BerlinClock  component using jest : check case when props is empty or not.
*/

describe('BerlinClock should mount successfully', () => {
    it('should mount with empty berlin clock string', () => {
        const {container} = render(<BerlinClock/>);
        expect(container.querySelector('div.berlin_clock_value').textContent.replace(/\s/g, '').length).toBe(0);
    });
    it('should mount with non empty berlin clock string', () => {
        const initialProps = {
            time: 'ORRROOOOOYYRYYROOOOOYYOO'
        };
        const {container} = render(<BerlinClock {...initialProps} />);
        expect(container.querySelector('div.berlin_clock_value').textContent.includes(initialProps.time)).toBe(true);
    });
});

