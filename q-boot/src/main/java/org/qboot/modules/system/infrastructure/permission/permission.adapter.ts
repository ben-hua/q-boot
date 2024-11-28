import { Injectable } from "@nestjs/common";
import { permission } from "../../core/domain/permission/permission.entity";
import { permissionRepository } from "./permission.repository";

interface permissionAdapterInterface {}

@Injectable()
export class permissionAdapter implements permissionAdapterInterface {
  constructor(private readonly permissionRepository: permissionRepository) {}
}
