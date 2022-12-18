import axios from 'axios';

const BASIC_URL = 'http://localhost:8080/'

export const getBerlinClockFromDigitalTime = (digitalTime) => {
    return axios.get(BASIC_URL+'getBerlinClock', { params: { digitalTime } });
}

export const getDigitalTimeFromBerlinClock = (berlinClock) => {
    return axios.get(BASIC_URL+'getDigitalTime', { params: { berlinClock } });
}
