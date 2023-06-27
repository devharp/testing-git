import { Controller, Get } from '@nestjs/common';
import { TmdbService } from './tmdb.service';

@Controller('tmdb')
export class TmdbController {
  constructor(private tmdbService: TmdbService) {}

  @Get('trending')
  public async getTrendingEntities() {
    return await this.tmdbService.fetchPopularEntities();
  }

  @Get('popular')
  public async getPopularEntities() {
    return await this.tmdbService.fetchPopularEntities();
  }
}
