import { EntityRepository, Repository } from "typeorm";
import { permission } from "../../core/domain/permission/permission.entity";

@EntityRepository(permission)
export class permissionRepository extends Repository<permission> {}
