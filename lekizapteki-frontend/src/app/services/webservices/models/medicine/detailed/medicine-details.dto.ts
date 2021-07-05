import {PricingDto} from './pricing.dto';

export interface MedicineDetailsDto {

  ean: string;
  name: string;
  activeIngredient: string;
  dose: string;
  form: string;
  pricing: PricingDto;

}
