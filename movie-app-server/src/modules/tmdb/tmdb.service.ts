import { HttpService } from '@nestjs/axios';
import { Injectable } from '@nestjs/common';

@Injectable()
export class TmdbService {
  private BASE_URL = 'https://api.themoviedb.org/3';
  private API_KEY = 'afb15c1692da8cd29350b576aefa98a3';
  constructor(private httpService: HttpService) {}
  public async fetchTrendingEntities() {
    return (
      await this.httpService
        .get(`${this.BASE_URL}/trending/all/day?api_key=${this.API_KEY}`)
        .toPromise()
    )['data'];
  }

  public async fetchPopularEntities() {
    return (
      await this.httpService
        .get(
          `${this.BASE_URL}/discover/movie?sort_by=popularity.desc&api_key=${this.API_KEY}`,
        )
        .toPromise()
    )['data'];
  }
}
