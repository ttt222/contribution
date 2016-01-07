//
//  JsonUtil.m
//  contribution
//
//  Created by 352 on 16/1/7.
//  Copyright © 2016年 352. All rights reserved.
//

#import "JsonUtil.h"

@implementation JsonUtil

+ (NSString *)DictionaryToJsonString:(NSDictionary *)dictionary {
    NSError *error = nil;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dictionary options:0 error:&error];
    if (error == nil && jsonData)
        return [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
    return NULL;
}

+ (NSMutableData *)DictionaryToMutableData:(NSDictionary *)dictionary {
    if ([NSJSONSerialization isValidJSONObject:dictionary]) {
        NSError *error = nil;
        NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dictionary options:0 error:&error];
        if (error == nil && jsonData)
            return [NSMutableData dataWithData:jsonData];
    }
    return NULL;
}

+ (NSDictionary *)JsonStringToDictionary:(NSString *)string {
    NSData *data = [string dataUsingEncoding:NSUTF8StringEncoding];
    NSError *error = nil;
    NSDictionary *json = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:&error];
    if (error == nil && json) {
        return json;
    }
    return NULL;
}

@end
