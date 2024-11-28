import { Injectable } from "@nestjs/common";
import { Dict } from "../../core/domain/dict/dict.entity";
import { DictRepository } from "./dict.repository";

interface DictAdapterInterface {}

@Injectable()
export class DictAdapter implements DictAdapterInterface {
  constructor(private readonly dictRepository: DictRepository) {}
}
