package engine;

public class Utils {
    public static SolDto convertCompletionEntityToDto(Solutions sol) {
        var completionDto = new SolDto();
        completionDto.setId(sol.getDBQuiz());
        //completionDto.setQuizTitle(completion.getQuiz().getTitle());
        completionDto.setCompletedAt(sol.getCompletedAt());
        return completionDto;
    }
}
