/**
 * @author Cristian Urbainski
 * @since 16/09/2019
 */
export class EnumeratorDTO {

    constructor(value : string, label : string) {
        this.value = value;
        this.label = label;
    }

    label : string;
    value : string;

}