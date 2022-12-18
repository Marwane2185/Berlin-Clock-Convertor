/*
  This file contains patterns for valid digital time and berlin clock formats.
*/
export const validTime = new RegExp('^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$');

export const validBerlinTime = new RegExp('^[YO][RO]{8}[YO]{2}[RO][YO]{2}[RO][YO]{2}[RO][YO]{6}$');