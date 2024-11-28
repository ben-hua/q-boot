import { EntityRepository, Repository } from "typeorm";
import { Dict } from "../../core/domain/dict/dict.entity";

@EntityRepository(Dict)
export class DictRepository extends Repository<Dict> {}
