package xyz.jdynb.dymovies.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VodLatestQueryParamsDto extends PageParams {

    private String flag;

    private Integer typeId;

    public static VodLatestQueryParamsDto create(Integer typeId, int page, int pageSize) {
        VodLatestQueryParamsDto vodLatestQueryParamsDto = new VodLatestQueryParamsDto();
        vodLatestQueryParamsDto.setTypeId(typeId);
        vodLatestQueryParamsDto.setPage(page);
        vodLatestQueryParamsDto.setPageSize(pageSize);
        return vodLatestQueryParamsDto;
    }

    public static VodLatestQueryParamsDto create(Integer typeId, int pageSize) {
        return create(typeId, 1, pageSize);
    }

}
