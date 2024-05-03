package com.graphQL.exception;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

@Component
public class UserGraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter{
	
	
	@Override
	protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
		
		if(ex instanceof UserGraphQLException) {
			
			return GraphqlErrorBuilder.newError()
					.errorType(ErrorType.NOT_FOUND)
					.message(ex.getMessage()+" - "+env.getArguments()+" - "+env.getGraphQLSchema()
					+ " - "+env.getVariables()+" - "+env.getDocument()
							)
					.path(env.getExecutionStepInfo().getPath())
					.location(env.getField().getSourceLocation())
					.build();
			
		}else {
			return super.resolveToSingleError(ex, env);
		}
		
	}

}
