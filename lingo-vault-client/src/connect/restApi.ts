import { Vocab } from '../dto/vocab/Vocab';
import axiosClient from "../config/axiosConfig";
import * as LingoVaultPath  from '../constant/LingoVaultPath';

export async function addNewVocabApi(newVocab: Vocab): Promise<any> {
   return  await axiosClient
        .post(LingoVaultPath.ADD_NEW_VOCAB ,newVocab)
        .then(response =>  response.data) 
}

export async function updateVocabApi(vocabList: Vocab[]): Promise<any> {
    return await axiosClient
        .post(LingoVaultPath.UPDATE_VOCAB ,vocabList)
        .then(response =>  response.data) 
}

export async function searchVocabApi(word: string): Promise<any> {
    return  await axiosClient
        .post(LingoVaultPath.SEARCH_VOCAB ,{"word": word})
        .then(response =>  response.data) 
}
